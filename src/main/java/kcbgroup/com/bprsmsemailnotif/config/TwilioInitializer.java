package kcbgroup.com.bprsmsemailnotif.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    @Autowired
    private final TwilioConfiguration twilioConfiguration;
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {

        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
    }
}
