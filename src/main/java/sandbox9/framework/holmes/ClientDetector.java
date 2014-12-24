package sandbox9.framework.holmes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public interface ClientDetector {
    ClientInfo detectDeviceInfo(String userAgentString);

    ClientInfo detectAll(HttpServletRequest servletRequest);
}
