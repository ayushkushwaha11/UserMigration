package com.niyogin.UserMigration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
public class User {

    @Id
    @Column(name = "userid")
    private int userid;

    @Column(name = "createdate")
    private LocalDateTime createdate;

    @Column(name = "screenname")
    private String screenname;

    @Column(name = "emailaddress")
    private String emailaddress;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "lastname")
    private String lastname;

}
