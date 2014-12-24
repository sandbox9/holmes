package sandbox9.framework.holmes;

import sandbox9.framework.holmes.profile.ClientRequestProfiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chanwook on 2014. 12. 24..
 */
public class RedirectTableClientRequestProfiler implements ClientRequestProfiler {

    public static final String TEST_HTTP_REDIRECT_URL = "http://tablet.site.com/main";

    @Override
    public boolean match(ClientInfo client) {
        //TODO Tablet 식별 로직 추가. android tablet 등
        if (ClientDevice.IPAD.equals(client.getDevice())) {
            return true;
        }
        return false;
    }

    @Override
    public void doProfile(ClientInfo client, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(TEST_HTTP_REDIRECT_URL);
    }
}
