package sandbox9.framework.holmes;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class ClientInfo implements Serializable {

    public static final String CLIENT_INFO_ATTRIBUTE_ID = "_HOLMES_CLIENT_INFO_ATTR_ID";

    private String source;

    private ClientDevice device;
    private ClientOS OS;
    private ClientBrowser browser;
    private String appVersion;

    public ClientInfo(String source) {
        this.source = source;
    }

    public void setDevice(ClientDevice device) {
        this.device = device;
    }

    public ClientDevice getDevice() {
        return device;
    }

    public void setOS(ClientOS OS) {
        this.OS = OS;
    }

    public ClientOS getOS() {
        return OS;
    }

    public void setBrowser(ClientBrowser browser) {
        this.browser = browser;
    }

    public ClientBrowser getBrowser() {
        return browser;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "source='" + source + '\'' +
                ", device=" + device +
                ", OS=" + OS +
                ", browser=" + browser +
                ", appVersion='" + appVersion + '\'' +
                '}';
    }
}
