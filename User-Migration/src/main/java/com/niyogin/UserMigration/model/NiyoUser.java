package com.niyogin.UserMigration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "niyo_user")
public class NiyoUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "niyouserid")
    private long niyouserid;

    @Column(name = "name")
    private String name;

    @Column(name = "usertype")
    private long userType;

    @Column(name = "userid")
    private long userid;

    @Column(name = "aadharno")
    private String aadharno;

    @Column(name = "panno")
    private String panno;

    @Column(name = "mobileno")
    private long mobileno;

    @Column(name = "password_")
    private String password_;

    @Column(name = "email")
    private String email;

    @Column(name = "cif")
    private long cif;

    @Column(name = "callyoushortly")
    private Boolean callyoushortly;

    @Column(name = "status")
    private String status;

    @Column(name = "partnerid")
    private String partnerid;

    @Column(name = "partnerstage_comment")
    private String partnerstage_comment;

    @Column(name = "modifieddate")
    private String modifieddate;

    @Column(name = "partnerclassification")
    private String partnerclassification;

    @Column(name = "sourcingclassification")
    private String sourcingclassification;

    @Column(name = "isu_id")
    private String isu_id;

    @Column(name = "subbrokerid")
    private String subbrokerid;

    @Column(name = "engagementclassification")
    private String engagementclassification;


}
