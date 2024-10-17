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
@Table(name = "niyo_user_details")
public class NiyoUserDetails {

    @Id
    @Column(name = "userdetailsid")
    private int niyoUserDetailsId;

    @Column(name = "userid")
    private int userId;

    @Column(name = "niyouserid")
    private int niyoUserId;

    @Column(name = "password_")
    private String password;

    @Column(name = "accesscode")
    private String accesscode;

    @Column(name = "nickname")
    private String nickName;


}
