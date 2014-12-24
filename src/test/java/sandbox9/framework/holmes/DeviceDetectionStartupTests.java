package sandbox9.framework.holmes;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;
import static sandbox9.framework.holmes.ClientBrowser.SAFARI;
import static sandbox9.framework.holmes.ClientDevice.IPHONE;
import static sandbox9.framework.holmes.ClientOS.IOS7;
import static sandbox9.framework.holmes.SimpleRegexDetector.HEADER_MOBILE_APP_VERSION;
import static sandbox9.framework.holmes.SimpleRegexDetector.HEADER_USER_AGENT;
import static sandbox9.framework.holmes.UserAgentTestData.IPHONE1;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class DeviceDetectionStartupTests {
    @Test
    public void simpleDetectionByRegex() throws Exception {
        ClientDetector detector = new SimpleRegexDetector();
//        ClientInfo client = detector.detectDeviceInfo(UserAgentTestData.IPHONE1);
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader(HEADER_USER_AGENT, IPHONE1);
        String appVersion = "1.17.3";
        servletRequest.addHeader(HEADER_MOBILE_APP_VERSION, appVersion);

        ClientInfo client = detector.detectAll(servletRequest);
        assertEquals(IPHONE, client.getDevice());
        assertEquals(SAFARI, client.getBrowser());
        assertEquals(IOS7, client.getOS());
        assertEquals(appVersion, client.getAppVersion());
    }
}
