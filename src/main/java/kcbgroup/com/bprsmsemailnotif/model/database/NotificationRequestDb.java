package kcbgroup.com.bprsmsemailnotif.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATION")
@Getter
@Setter
public class NotificationRequestDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String MessageID;
    private String phoneNumber;
    private String message;
    private String emailAddress;
    private String email;
    private String serviceName;

}
