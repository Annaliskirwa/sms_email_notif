package kcbgroup.com.bprsmsemailnotif.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequest {
    @NotBlank
    private  String MessageID;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String message;
}

