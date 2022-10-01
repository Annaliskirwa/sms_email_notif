package kcbgroup.com.bprsmsemailnotif.controller;

import kcbgroup.com.bprsmsemailnotif.model.body.SmsRequest;
import kcbgroup.com.bprsmsemailnotif.service.SmsNotifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class SmsNotifController {
    @Autowired
    SmsNotifService smsNotifService;

    @PostMapping(value = "sms-notif")
    public String sendSMS(@RequestBody String request){
        log.info("****THE REQUEST***{}", request);
        return smsNotifService.sendSms(request);
    }
}
