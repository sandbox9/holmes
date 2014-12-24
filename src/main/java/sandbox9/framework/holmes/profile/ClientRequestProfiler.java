package sandbox9.framework.holmes.profile;

import sandbox9.framework.holmes.ClientInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chanwook on 2014. 12. 24..
 */
public interface ClientRequestProfiler {
    boolean match(ClientInfo client);

    void doProfile(ClientInfo client, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
