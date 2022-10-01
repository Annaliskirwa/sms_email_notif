package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import kcbgroup.com.bprsmsemailnotif.model.database.NotificationRequestDb;
import kcbgroup.com.bprsmsemailnotif.repository.NotifRepository;
import kcbgroup.com.bprsmsemailnotif.service.SmsOperations;
import kcbgroup.com.bprsmsemailnotif.service.TwilioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsOperationImpl implements SmsOperations {
    @Autowired
    NotifRepository smsRepository;
    @Autowired
    TwilioService twilioService;
    @Override
    public void smsOperation(SmsRequest smsRequest) {
        NotificationRequestDb smsRequest1 = new NotificationRequestDb();
        smsRequest1.setMessage(smsRequest.getMessage());
        smsRequest1.setMessageID(smsRequest.getMessageID());
        smsRequest1.setPhoneNumber(smsRequest.getPhoneNumber());
        smsRequest1.setServiceName("SMS");
        smsRepository.save(smsRequest1);

        twilioService.sendSms(smsRequest);
    }
}
