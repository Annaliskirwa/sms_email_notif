package kcbgroup.com.bprsmsemailnotif.service;

import org.springframework.stereotype.Service;

@Service
public interface SmsNotifService {
    String sendSms(String smsRequest);
}
