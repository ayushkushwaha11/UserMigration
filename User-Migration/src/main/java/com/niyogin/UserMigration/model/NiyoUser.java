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
@Table(name = "niyo_user")
public class NiyoUser {

    @Id
    @Column(name = "niyouserid")
    private int niyoUserId;

    @Column(name = "name")
    private String name;

    @Column(name = "usertype")
    private int userType;

    @Column(name = "userid")
    private int userId;

    @Column(name = "mobileno")
    private String mobileno;

    @Column(name = "email")
    private String email;

    @Column(name = "cif")
    private String cif;

    @Column(name = "aadharno")
    private String aadharno;

    @Column(name = "panno")
    private String panno;
}
