package com.niyogin.UserMigration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
public class User {

    @Id
    @Column(name = "userid")
    private long userid;

    @Column(name = "createdate")
    private String createdate;

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
