package com.niyogin.UserMigration.service;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;

import java.util.List;

public interface UserCustomerCompanyService {
    public NiyoCustomerCompany getNiyoCustomerByNiyoCustomerId(long niyoCustomerCompanyId);
    public String addCustomerCompanyDetailsService(String companyName, String pincode, String industryType, String industrySubType, String mobileNo, String email, long niyoPartnerCompanyId);
}
