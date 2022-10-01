package kcbgroup.com.bprsmsemailnotif.controller;

import kcbgroup.com.bprsmsemailnotif.service.EmailNotifService;
import kcbgroup.com.bprsmsemailnotif.service.SmsNotifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class EmailController {
    @Autowired
    EmailNotifService emailNotifService;

    @PostMapping(value = "email-notif")
    public String sendSMS(@RequestBody String request){
        log.info("****THE REQUEST***{}", request);
        return emailNotifService.sendEmail(request);
    }
}