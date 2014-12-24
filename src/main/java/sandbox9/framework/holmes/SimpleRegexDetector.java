package sandbox9.framework.holmes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static sandbox9.framework.holmes.ClientBrowser.*;
import static sandbox9.framework.holmes.ClientDevice.*;
import static sandbox9.framework.holmes.ClientOS.*;

/**
 * UserAgent 문자열을 분석해 클라이언트의 정보를 찾아줌.
 * 가장 쉬운 문자열 매칭 방법을 사용해 전체 디바이스의 90% 이상의 식별율을 보여준다
 * <p/>
 * Created by chanwook on 2014. 12. 23..
 */
public class SimpleRegexDetector implements ClientDetector {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_MOBILE_APP_VERSION = "x-mobile-app-version";
    public static final String DEFAULT_APP_VERSION = "NoSpecifiedVersion";

    @Override
    public ClientInfo detectAll(HttpServletRequest servletRequest) {
        String userAgentString = servletRequest.getHeader(HEADER_USER_AGENT);
        ClientInfo client = detectDeviceInfo(userAgentString);

        String appVersion = detectAppVersion(servletRequest, client);
        client.setAppVersion(appVersion);
        return client;
    }

    public String detectAppVersion(HttpServletRequest servletRequest, ClientInfo client) {
        String appVersionHeader = servletRequest.getHeader(HEADER_MOBILE_APP_VERSION);
        if (appVersionHeader == null || appVersionHeader.length() < 1) {
            logger.warn("APP Version이 지정되지 않은 사용자 요청이 들어왔습니다!(UA: " + client.getSource() + ")");
            return DEFAULT_APP_VERSION;
        }
        return appVersionHeader;
    }

    @Override
    public ClientInfo detectDeviceInfo(String userAgentString) {
        ClientInfo client = new ClientInfo(userAgentString);
        detectDevice(userAgentString, client);
        detectOS(userAgentString, client);
        detectBrowser(userAgentString, client);
        return client;
    }


    protected void detectBrowser(String userAgentString, ClientInfo client) {
        //TODO android browser는 상세하게 식별하기 위해 OS 버전과 콜라보로 식별할 수 있도록 추가함
        if (userAgentString.matches(".*(Safari/).*")) {
            client.setBrowser(SAFARI);
        } else if (userAgentString.matches(".*(NetFront/).*")) {
            client.setBrowser(NETFRONT);
        } else if (userAgentString.matches(".*(Opera Mini/).*")) {
            client.setBrowser(OPERA_MINI);
        } else if (userAgentString.matches(".*(Opera Mobi/).*")) {
            client.setBrowser(OPERA_MOBI);
        } else if (userAgentString.matches(".*(Dolfin/||Dolphin/).*")) {
            client.setBrowser(DOLFIN);
        } else if (userAgentString.matches(".*(Silk/||Silk-Accelerated/).*")) {
            client.setBrowser(SILK);
        }
    }

    protected void detectOS(String userAgentString, ClientInfo client) {
        //TODO 설정 파일로 분리
        //TODO ios와 aos의 상세 버전을 추출해서 넣어주는 방식으로 변경
        if (userAgentString.matches(".*(iPhone OS 4).*")) {
            client.setOS(IOS4);
        } else if (userAgentString.matches(".*(iPhone OS 5).*")) {
            client.setOS(IOS5);
        } else if (userAgentString.matches(".*(iPhone OS 6).*")) {
            client.setOS(IOS6);
        } else if (userAgentString.matches(".*(iPhone OS 7).*")) {
            client.setOS(IOS7);
        } else if (userAgentString.matches(".*(iPhone OS 8).*")) {
            client.setOS(IOS8);
        } else if (userAgentString.matches(".*(Android 2).*")) {
            client.setOS(ANDROID2);
        } else if (userAgentString.matches(".*(Android 4).*")) {
            client.setOS(ANDROID4);
        } else if (userAgentString.matches(".*(Linux).*")) {
            client.setOS(LINUX);
        } else if (userAgentString.matches(".*(X11||Windows).*")) {
            client.setOS(WINDOWS);
        } else if (userAgentString.matches(".*(Macintosh).*")) {
            client.setOS(MACOSX);
        }
    }

    protected void detectDevice(String userAgentString, ClientInfo client) {
        //TODO 설정 파일로 분리
        if (userAgentString.matches(".*(\\(iPhone).*")) {
            client.setDevice(IPHONE);
        } else if (userAgentString.matches(".*(\\(iPad).*")) {
            client.setDevice(IPAD);
        } else if (userAgentString.matches(".*(\\(iPod).*")) {
            client.setDevice(IPOD);
        } else if (userAgentString.matches(".*(Android).*(Mobile).*")) {
            client.setDevice(ANDROID);
        } else if (userAgentString.matches(".*(BlackBerry).*")) {
            client.setDevice(BLACKBERRY);
        } else if (userAgentString.matches(".*(IEMobile).*")) {
            client.setDevice(WINDOW);
        } else if (userAgentString.matches(".*(Kindle).*")) {
            client.setDevice(KINDLE);
        } else if (isAndroidTablet(userAgentString)) {
            client.setDevice(ANDROID_TABLET);
        } else if (userAgentString.matches(".*(Linux||X11||Windows||Macintosh).*")) {
            client.setDevice(PC);
        } else {
            client.setDevice(NOT_IDENTIFIED);
        }
    }

    private boolean isAndroidTablet(String userAgentString) {
        //TODO UA와 Screen size를 조합해 식별 필요
        return userAgentString.matches(".*(블라블라).*");
    }
}
