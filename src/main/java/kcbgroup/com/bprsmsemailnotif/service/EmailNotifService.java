package kcbgroup.com.bprsmsemailnotif.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailNotifService {

    String sendEmail(String emailRequest);
}
