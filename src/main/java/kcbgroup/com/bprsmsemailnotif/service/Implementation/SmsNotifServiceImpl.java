package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import kcbgroup.com.bprsmsemailnotif.producer.SmsProducer;
import kcbgroup.com.bprsmsemailnotif.service.SmsNotifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Component
@Slf4j
public class SmsNotifServiceImpl implements SmsNotifService {

    @Autowired
    SmsProducer smsProducer;

    @Override
    public String sendSms(String smsRequest) {
        SmsRequest smsRequest1 = new SmsRequest();
        smsRequest1.setMessage(StringUtils.substringBetween(smsRequest, "<message>", "</message>"));
        smsRequest1.setMessageID(StringUtils.substringBetween(smsRequest, "<messageID>", "</messageID>"));
        smsRequest1.setPhoneNumber(StringUtils.substringBetween(smsRequest, "<businessKey>", "</businessKey>"));

        log.info("Sms request{}", smsRequest1);
        smsProducer.sendResponseToQueue(smsRequest1);

        return smsRequest;
    }
}
