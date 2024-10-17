package com.niyogin.UserMigration.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "niyo_customer_companyusers")
public class NiyoCustomerCompanyUser {
    @Id
    @Column(name = "niyocustomercompanyusersid")
    private int niyoCustomerCompanyUserId;

    @Column(name = "niyouserid")
    private int niyoUserId;

    @Column(name = "niyocustomercompanyid")
    private int niyoCustomerCompanyId;
}
