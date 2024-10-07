package com.niyogin.UserMigration.service.impl;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;
import com.niyogin.UserMigration.model.NiyoUser;
import com.niyogin.UserMigration.repo.CounterRepo;
import com.niyogin.UserMigration.repo.NiyoCustomerCompanyRepo;
import com.niyogin.UserMigration.repo.NiyoUserRepo;
import com.niyogin.UserMigration.service.UserCustomerCompanyService;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.function.AvgFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.List;

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

    @Override
    public List<NiyoCustomerCompany> getAllNiyoCustomerCompany() {
        return niyoCustomerCompanyRepo.getEmpList();
    }

    @Override
    public NiyoCustomerCompany getNiyoCustomerByNiyoCustomerId(long niyoCustomerCompanyId) {
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

        try {
            userEmail = niyoCustomerCompanyRepo.findByEmail(email).getEmail();
            niyoUser = niyoUserRepo.findByEmail(email);
        }catch (NullPointerException e){
            _log.info("Creating Customer Company :) ");
        }
        String status ="";

//        String niyoUserEmail
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

    @Override
    public String addCustomerUser(String name, String mobileNo, String email, String cif, long niyoCustomerCompanyId, Boolean consent) {
        String aadharNo = "";
        String panNo = "";
        String passsword = "";
        String niyoUserId = "";
        long userId = 0;
        String accessCode = "";
        String nickName = "";
        NiyoUser niyoUser = null;


        return "testing";
    }

}
