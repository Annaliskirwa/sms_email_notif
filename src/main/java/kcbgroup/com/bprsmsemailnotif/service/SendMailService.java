package kcbgroup.com.bprsmsemailnotif.service;

import kcbgroup.com.bprsmsemailnotif.model.database.NotificationRequestDb;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SendMailService {
    String sendMail(NotificationRequestDb notificationRequestDb);
}