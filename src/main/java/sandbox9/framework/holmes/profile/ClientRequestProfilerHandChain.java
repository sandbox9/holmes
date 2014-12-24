package sandbox9.framework.holmes.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chanwook on 2014. 12. 24..
 */
public interface ClientRequestProfilerHandChain {
    void process(HttpServletRequest request, HttpServletResponse response);

    void addProfiler(ClientRequestProfiler profiler);
}
