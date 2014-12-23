package sandbox9.framework.holmes;

/**
 * UserAgent 문자열을 분석해 클라이언트의 정보를 찾아줌.
 * 가장 쉬운 문자열 매칭 방법을 사용해 전체 디바이스의 90% 이상의 식별율을 보여준다
 * <p/>
 * Created by chanwook on 2014. 12. 23..
 */
public class SimpleRegexDetector implements ClientDetector {

    @Override
    public ClientInfo detect(String userAgentString) {
        ClientInfo client = new ClientInfo();
        detectDevice(userAgentString, client);
        detectOS(userAgentString, client);
        detectBrowser(userAgentString, client);
        return client;
    }

    protected void detectBrowser(String userAgentString, ClientInfo client) {
        //TODO android browser는 상세하게 식별하기 위해 OS 버전과 콜라보로 식별할 수 있도록 추가함
        if (userAgentString.matches(".*(Safari/).*")) {
            client.setBrowser(ClientBrowser.SAFARI);
        } else if (userAgentString.matches(".*(NetFront/).*")) {
            client.setBrowser(ClientBrowser.NETFRONT);
        } else if (userAgentString.matches(".*(Opera Mini/).*")) {
            client.setBrowser(ClientBrowser.OPERA_MINI);
        } else if (userAgentString.matches(".*(Opera Mobi/).*")) {
            client.setBrowser(ClientBrowser.OPERA_MOBI);
        } else if (userAgentString.matches(".*(Dolfin/||Dolphin/).*")) {
            client.setBrowser(ClientBrowser.DOLFIN);
        } else if (userAgentString.matches(".*(Silk/||Silk-Accelerated/).*")) {
            client.setBrowser(ClientBrowser.SILK);
        }
    }

    protected void detectOS(String userAgentString, ClientInfo client) {
        //TODO 설정 파일로 분리
        //TODO ios와 aos의 상세 버전을 추출해서 넣어주는 방식으로 변경
        if (userAgentString.matches(".*(iPhone OS 4).*")) {
            client.setOS(ClientOS.IOS4);
        } else if (userAgentString.matches(".*(iPhone OS 5).*")) {
            client.setOS(ClientOS.IOS5);
        } else if (userAgentString.matches(".*(iPhone OS 6).*")) {
            client.setOS(ClientOS.IOS6);
        } else if (userAgentString.matches(".*(iPhone OS 7).*")) {
            client.setOS(ClientOS.IOS7);
        } else if (userAgentString.matches(".*(iPhone OS 8).*")) {
            client.setOS(ClientOS.IOS8);
        } else if (userAgentString.matches(".*(Android 2).*")) {
            client.setOS(ClientOS.ANDROID2);
        } else if (userAgentString.matches(".*(Android 4).*")) {
            client.setOS(ClientOS.ANDROID4);
        } else if (userAgentString.matches(".*(Linux).*")) {
            client.setOS(ClientOS.LINUX);
        } else if (userAgentString.matches(".*(X11||Windows).*")) {
            client.setOS(ClientOS.WINDOWS);
        } else if (userAgentString.matches(".*(Macintosh).*")) {
            client.setOS(ClientOS.MACOSX);
        }
    }

    protected void detectDevice(String userAgentString, ClientInfo client) {
        //TODO 설정 파일로 분리
        if (userAgentString.matches(".*(iPhone).*")) {
            client.setDevice(ClientDevice.IPHONE);
        } else if (userAgentString.matches(".*(iPad).*")) {
            client.setDevice(ClientDevice.IPAD);
        } else if (userAgentString.matches(".*(iPod).*")) {
            client.setDevice(ClientDevice.IPOD);
        } else if (userAgentString.matches(".*(Android).*")) {
            client.setDevice(ClientDevice.ANDROID);
        } else if (userAgentString.matches(".*(BlackBerry).*")) {
            client.setDevice(ClientDevice.BLACKBERRY);
        } else if (userAgentString.matches(".*(IEMobile).*")) {
            client.setDevice(ClientDevice.WINDOW);
        } else if (userAgentString.matches(".*(Kindle).*")) {
            client.setDevice(ClientDevice.KINDLE);
        } else if (userAgentString.matches(".*(Kindle).*")) {
            client.setDevice(ClientDevice.KINDLE);
        } else if (userAgentString.matches(".*(Linux||X11||Windows||Macintosh).*")) {
            client.setDevice(ClientDevice.PC);
        } else {
            client.setDevice(ClientDevice.NO_IDENTIFIED);
        }
    }
}
