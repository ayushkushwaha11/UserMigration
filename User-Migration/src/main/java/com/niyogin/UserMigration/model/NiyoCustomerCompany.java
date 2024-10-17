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
    private int niyoCustomerCompanyId;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "industrytype")
    private String industryType;

    @Column(name = "industrysubtype")
    private String industrySubType;

    @Column(name = "mobileno")
    private String  mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "niyopartnercompanyid")
    private int niyoPartnerCompanyId;

}
