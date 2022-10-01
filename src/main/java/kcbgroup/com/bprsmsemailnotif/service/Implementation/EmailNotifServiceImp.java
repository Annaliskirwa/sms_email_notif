package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import kcbgroup.com.bprsmsemailnotif.model.body.EmailRequest;
import kcbgroup.com.bprsmsemailnotif.producer.EmailProducer;
import kcbgroup.com.bprsmsemailnotif.service.EmailNotifService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotifServiceImp implements EmailNotifService {

    @Autowired
    EmailProducer emailProducer;

    @Override
    public String sendEmail(String emailRequest) {

        EmailRequest emailRequest1 = new EmailRequest();
        emailRequest1.setEmail(StringUtils.substringBetween(emailRequest, "<message>", "</message>"));
        emailRequest1.setMessageID(StringUtils.substringBetween(emailRequest, "<messageID>", "</messageID>"));
        emailRequest1.setEmailAddress(StringUtils.substringBetween(emailRequest, "<businessKey>", "</businessKey>"));

        log.info("Sms request{}", emailRequest1);
        emailProducer.sendResponseToQueue(emailRequest1);

        return emailRequest;
    }
}
