package sandbox9.framework.holmes.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.framework.holmes.ClientInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static sandbox9.framework.holmes.ClientInfo.CLIENT_INFO_ATTRIBUTE_ID;

/**
 * Created by chanwook on 2014. 12. 24..
 */
public class SimpleClientRequestProfilerHandlChain implements ClientRequestProfilerHandChain {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<ClientRequestProfiler> profilerList = new ArrayList<ClientRequestProfiler>();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        Object clientInfoObject = request.getAttribute(CLIENT_INFO_ATTRIBUTE_ID);
        if (clientInfoObject == null) {
            throw new RuntimeException("Profiler 수행을 위해서는 Client Info가 먼저 생성되서 HttpRequest.attribute로 설정되어 있어야 합니다.");
        }

        ClientInfo client = (ClientInfo) clientInfoObject;

        try {
            for (ClientRequestProfiler profiler : profilerList) {
                if (profiler.match(client)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(profiler + "을 실행 (source: " + client + ")");
                    }
                    profiler.doProfile(client, request, response);
                }
            }
        } catch (Exception e) {
            //FIXME re-throw 안 하도록?
            throw new RuntimeException("Profiler 수행 중에 에러가 발생했습니다", e);
        }
    }

    @Override
    public void addProfiler(ClientRequestProfiler profiler) {
        this.profilerList.add(profiler);
    }
}
