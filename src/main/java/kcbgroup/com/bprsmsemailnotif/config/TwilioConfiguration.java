package kcbgroup.com.bprsmsemailnotif.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

    //fields used to get the properties into our class

    private String accountSid;
    private String authToken;
    private String trialNumber;
}
