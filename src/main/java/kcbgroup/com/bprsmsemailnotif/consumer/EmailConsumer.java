package kcbgroup.com.bprsmsemailnotif.consumer;

import com.google.gson.Gson;
import kcbgroup.com.bprsmsemailnotif.model.body.EmailRequest;
import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import kcbgroup.com.bprsmsemailnotif.service.EmailOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;


@Component
@Slf4j
public class EmailConsumer {
    @Autowired
    EmailOperations emailOperations;

    @JmsListener(containerFactory = "myJmsListenerContainerFactory", destination = "${notif.amq-email-queue-name}")
    public void onMessage(Message message) throws JMSException {
        try {
            Gson gson = new Gson();
            String request = null;
            request = ((MapMessage) message).getString("message");
            log.info("AMQ EMAIL Consumer Payload : {}", request);
            EmailRequest emailRequest = gson.fromJson(request, EmailRequest.class);
            log.info("{}",emailRequest);
            emailOperations.emailOperation(emailRequest);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
