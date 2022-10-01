package kcbgroup.com.bprsmsemailnotif.service.Implementation;

import freemarker.core.ParseException;
import freemarker.template.*;
import kcbgroup.com.bprsmsemailnotif.model.database.NotificationRequestDb;
import kcbgroup.com.bprsmsemailnotif.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    @Override
    public String sendMail(NotificationRequestDb notificationRequestDb) {
       String response;
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Template template = configuration.getTemplate("email.ftl");
            Map<String, String> model = new HashMap<>();
            model.put("value", "Transaction report");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(notificationRequestDb.getEmailAddress());
            helper.setFrom("bonipo9839@migonom.com");
            helper.setSubject("TRANSACTION REPORT");
            helper.setText(html, true);

            log.info("-------------{}",helper);

            javaMailSender.send(message);
            response = "Email has been sent to :" + notificationRequestDb.getEmailAddress();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
