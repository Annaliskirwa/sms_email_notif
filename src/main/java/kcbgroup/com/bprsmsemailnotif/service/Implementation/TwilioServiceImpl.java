package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import kcbgroup.com.bprsmsemailnotif.config.TwilioConfiguration;
import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import kcbgroup.com.bprsmsemailnotif.service.TwilioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class TwilioServiceImpl implements TwilioService {
    @Autowired
    TwilioConfiguration twilioConfiguration;
    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
        }
        else{
            log.error("INVALID PHONE NUMBER");
        }
    }
    private boolean isPhoneNumberValid(String phoneNumber) {
        String countryCodeRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        Pattern pattern = Pattern.compile(countryCodeRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        matcher.matches();
        return true;
    }
}
