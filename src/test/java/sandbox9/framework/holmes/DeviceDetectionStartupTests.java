package sandbox9.framework.holmes;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import sandbox9.framework.holmes.profile.ClientRequestProfilerHandChain;
import sandbox9.framework.holmes.profile.SimpleClientRequestProfilerHandlChain;

import static org.junit.Assert.assertEquals;
import static sandbox9.framework.holmes.ClientBrowser.SAFARI;
import static sandbox9.framework.holmes.ClientDevice.IPHONE;
import static sandbox9.framework.holmes.ClientOS.IOS7;
import static sandbox9.framework.holmes.RedirectTableClientRequestProfiler.TEST_HTTP_REDIRECT_URL;
import static sandbox9.framework.holmes.SimpleRegexDetector.HEADER_MOBILE_APP_VERSION;
import static sandbox9.framework.holmes.SimpleRegexDetector.HEADER_USER_AGENT;
import static sandbox9.framework.holmes.UserAgentTestData.IPHONE1;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class DeviceDetectionStartupTests {
    ClientDetector detector = new SimpleRegexDetector();

    @Test
    public void simpleDetectionByRegex() throws Exception {
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

    @Test
    public void profile() throws Exception {
        // 예시 시나리오: Ipad일 때 phone size로 들어 왔으면 pad size url로 redirect
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        req.addHeader(HEADER_USER_AGENT, UserAgentTestData.IPAD1);

        ClientInfo client = detector.detectAll(req);
        req.setAttribute(ClientInfo.CLIENT_INFO_ATTRIBUTE_ID, client);

        ClientRequestProfilerHandChain chain = new SimpleClientRequestProfilerHandlChain();
        chain.addProfiler(new RedirectTableClientRequestProfiler());
        chain.process(req, res);

        assertEquals(TEST_HTTP_REDIRECT_URL, res.getRedirectedUrl());
    }
}
