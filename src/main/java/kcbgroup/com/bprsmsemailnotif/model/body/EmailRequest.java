package kcbgroup.com.bprsmsemailnotif.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    @NotBlank
    private  String MessageID;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String email;
}
