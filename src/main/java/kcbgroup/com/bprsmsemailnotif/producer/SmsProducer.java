package kcbgroup.com.bprsmsemailnotif.producer;

import com.google.gson.Gson;
import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;

@Component
@Slf4j
public class SmsProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.broker-url}")
    private String BROKER_URL;

    @Value("${notif.amq-receive-timeout}")
    private int AMQ_RECEIVE_TIMEOUT;

    @Value("${notif.amq-sms-queue-name}")
    private String BPR_SMS_QUEUE;

    public void sendResponseToQueue(SmsRequest request) {
        Gson gson = new Gson();
        String smsMessage = gson.toJson(request);

        String queName = BPR_SMS_QUEUE;
        log.info("ActiveMq SMS QueueName : {}", queName);
        log.info("ActiveMq SMS Request Payload : {}", smsMessage);
        log.info("//****************************************");

        // TODO @Send to ActiveMQ
        jmsTemplate.setMessageIdEnabled(true);
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.send(queName, session -> {
            MapMessage message = session.createMapMessage();
            message.setString("message", smsMessage);
            message.setJMSCorrelationID(request.getMessageID());
            message.setJMSMessageID(request.getMessageID());
            message.setJMSType("text");
            return message;
        });

    }
}
