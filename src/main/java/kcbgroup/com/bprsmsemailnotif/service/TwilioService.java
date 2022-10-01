package kcbgroup.com.bprsmsemailnotif.service;

import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface TwilioService {
    void sendSms(SmsRequest smsRequest);
}
