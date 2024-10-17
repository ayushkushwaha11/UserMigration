package com.niyogin.UserMigration.service.impl;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;
import com.niyogin.UserMigration.repo.CounterRepo;
import com.niyogin.UserMigration.repo.NiyoCustomerCompanyRepo;
import com.niyogin.UserMigration.repo.NiyoUserRepo;
import com.niyogin.UserMigration.repo.UserRepo;
import com.niyogin.UserMigration.service.UserCustomerCompanyService;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomerCompanyServiceImpl implements UserCustomerCompanyService {

    private static final Logger _log = LoggerFactory.getLogger(UserCustomerCompanyServiceImpl.class);
    @Autowired
    private NiyoCustomerCompanyRepo niyoCustomerCompanyRepo;

    @Autowired
    private NiyoUserRepo niyoUserRepo;

    @Autowired
    private CounterRepo counterRepo;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserRepo userRepo;

    @Override
    public NiyoCustomerCompany getNiyoCustomerByNiyoCustomerId(long niyoCustomerCompanyId) {
        System.out.println(niyoCustomerCompanyRepo.getCustomerCompanyByCustomerId(niyoCustomerCompanyId).toString());
        return niyoCustomerCompanyRepo.getCustomerCompanyByCustomerId(niyoCustomerCompanyId);
    }

    @Transactional
    @Override
    public String addCustomerCompanyDetailsService(String companyName, String pincode, String industryType, String industrySubType, String mobileNo, String email, long niyoPartnerCompanyId) {
        _log.info("Calling Model Lister From Spring Boot");
        _log.info("Before caching");
        sessionFactory.getCache().evictAllRegions(); // Deleting the DB Cache

        counterRepo.incrementCounter("com.liferay.counter.kernel.model.Counter");

        String userEmail = "";
        String niyoUser = "";

        //If email is not exist in User table then company Details will generate
        try {
            userEmail = userRepo.findByEmail(email).getEmailaddress();
            niyoUser = niyoUserRepo.findByEmail(email);
        }catch (NullPointerException e){
            _log.info("Creating Customer Company :) ");
        }
        String status ="";

        _log.info(userEmail);

        NiyoCustomerCompany niyoCustomerCompany = new NiyoCustomerCompany();

        if(userEmail.isEmpty() && niyoUser.isEmpty()){
            _log.info("Adding Niyo Customer Company has started");
            long niyoCustomerID = counterRepo.findByName("com.liferay.counter.kernel.model.Counter").getCurrentid();
            niyoCustomerCompany.setNiyoCustomerCompanyId(niyoCustomerID);
            niyoCustomerCompany.setCompanyName(companyName);
            niyoCustomerCompany.setPincode(Long.parseLong(pincode));
            niyoCustomerCompany.setIndustryType(Long.parseLong(industryType));
            niyoCustomerCompany.setIndustrySubType(Long.parseLong(industrySubType));
            niyoCustomerCompany.setMobileNo(mobileNo);
            niyoCustomerCompany.setEmail(email);
            niyoCustomerCompany.setNiyoPartnerCompanyId(niyoPartnerCompanyId);

            niyoCustomerCompanyRepo.save(niyoCustomerCompany);
            status = niyoCustomerCompany.toString();

            _log.info("Customer company created : ");
            _log.info("Response : "+status);


        }else {
            try {
                _log.info("User is existing with the same email "+ email);
            }catch (Exception e){
                e.printStackTrace();
            }

            status = "User already exists with requested email.";
        }
        return status;
    }

}
