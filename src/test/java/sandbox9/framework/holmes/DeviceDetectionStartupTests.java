package sandbox9.framework.holmes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class DeviceDetectionStartupTests {
    @Test
    public void simpleDetectionByRegex() throws Exception {
        ClientDetector detector = new SimpleRegexDetector();
        ClientInfo client = detector.detect(UserAgentData.IPHONE1);

        assertEquals(ClientDevice.IPHONE, client.getDevice());

    }
}
