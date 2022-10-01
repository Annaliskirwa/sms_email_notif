package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import kcbgroup.com.bprsmsemailnotif.model.body.EmailRequest;
import kcbgroup.com.bprsmsemailnotif.model.database.NotificationRequestDb;
import kcbgroup.com.bprsmsemailnotif.repository.NotifRepository;
import kcbgroup.com.bprsmsemailnotif.service.EmailOperations;
import kcbgroup.com.bprsmsemailnotif.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailOperationImpl implements EmailOperations {
    @Autowired
    NotifRepository emailRepository;

    @Autowired
    SendMailService sendMailService;
    @Override
    public void emailOperation(EmailRequest emailRequest) {
        NotificationRequestDb emailRequest1 = new NotificationRequestDb();
        emailRequest1.setEmail(emailRequest.getEmail());
        emailRequest1.setMessageID(emailRequest.getMessageID());
        emailRequest1.setEmailAddress(emailRequest.getEmailAddress());
        emailRequest1.setServiceName("EmailNotification");
        emailRepository.save(emailRequest1);

        sendMailService.sendMail(emailRequest1);
    }
}
