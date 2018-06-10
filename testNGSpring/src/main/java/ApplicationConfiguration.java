import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Value("${rest.accesstoken}")
    private String accesstoken;
    @Value("${rest.appid}")
    private String appid;

    @Value("${rest.authn_url}")
    private String authn_url;

    @Value("${rest.dng_host}")
    private String dng_host;

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getAppid() {
        return appid;
    }

    public String getAuthn_url() {
        return authn_url;
    }

    public String getDng_host() {
        return dng_host;
    }
}