package com.niyogin.UserMigration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "niyo_customer_company")
public class NiyoCustomerCompany {

    @Id
    @Column(name = "niyocustomercompanyid")
    private long niyoCustomerCompanyId;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "pincode")
    private long pincode;

    @Column(name = "industrytype")
    private long industryType;

    @Column(name = "industrysubtype")
    private long industrySubType;

    @Column(name = "mobileno")
    private String  mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "niyopartnercompanyid")
    private long niyoPartnerCompanyId;

}
