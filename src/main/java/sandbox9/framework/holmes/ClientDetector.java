package sandbox9.framework.holmes;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public interface ClientDetector {
    ClientInfo detect(String userAgentString);
}
