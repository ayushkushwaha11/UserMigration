package com.niyogin.UserMigration.service.impl;

import com.niyogin.UserMigration.model.*;
import com.niyogin.UserMigration.repo.*;
import com.niyogin.UserMigration.service.UserCustomerCompanyService;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.function.AvgFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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

    @Autowired
    private NiyoUserDetailsRepo niyoUserDetailsRepo;

    @Autowired
    private NiyoCustomerCompanyUserRepo niyoCustomerCompanyUserRepo;

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
    public String addCustomerCompanyDetails(String companyName, String pincode, String industryType, String industrySubType, String mobileNo, String email, long niyoPartnerCompanyId) {
        _log.info("Calling Model Lister From Spring Boot");
        _log.info("Before caching");
        sessionFactory.getCache().evictAllRegions(); // Deleting the DB Cache
        counterRepo.incrementCounter("com.liferay.counter.kernel.model.Counter");


        String userEmail = "";
        String niyoUser = "";

        try {
            userEmail = userRepo.findByEmail(email).getEmailaddress();
            niyoUser = niyoUserRepo.findByEmail(email).get(0).getEmail();
        }catch (NullPointerException e){
            e.printStackTrace();
            _log.info("Creating Customer Company :) ");
        }
        String status ="";

//        String niyoUserEmail
        _log.info(userEmail);

        NiyoCustomerCompany niyoCustomerCompany = new NiyoCustomerCompany();

        try {
            // Checking Email in User and NiyoUser table
            if(userEmail.isBlank() && niyoUser.isBlank()){
                _log.info("Adding Niyo Customer Company has started");
                long niyoCustomerID = (counterRepo.findByName("com.liferay.counter.kernel.model.Counter").getCurrentid())-99;
                niyoCustomerCompany.setNiyoCustomerCompanyId((int)niyoCustomerID);
                niyoCustomerCompany.setCompanyName(companyName);
                niyoCustomerCompany.setPincode(String.valueOf(pincode));
                niyoCustomerCompany.setIndustryType(String.valueOf(industryType));
                niyoCustomerCompany.setIndustrySubType(String.valueOf(industrySubType));
                niyoCustomerCompany.setMobileNo(mobileNo);
                niyoCustomerCompany.setEmail(email);
                niyoCustomerCompany.setNiyoPartnerCompanyId((int)niyoPartnerCompanyId);

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
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    @Transactional
    @Override
    public String addCustomerUser(String name, String mobileNo, String email, String cif, long niyoCustomerCompanyId, boolean consent) {
        String aadharNo = "";
        String panNo = "";
        String passsword = "";
        long niyoUserId = 0;
        long userId = 0;
        User user = new User();
        String accessCode = "";
        String nickName = "";
        NiyoUser niyoUser = new NiyoUser();

        long niyoCustomerCompanyUserId = 0;
        NiyoCustomerCompanyUser niyoCustomerCompanyUser = new NiyoCustomerCompanyUser();


        try {
            if(email == "" || email == null){
                email = name.toLowerCase(Locale.ROOT).replace(" ","_")+"@niyogin.in";
                _log.info("email : ====== : "+email);
            }
            //Adding USER
            counterRepo.incrementCounter("com.liferay.counter.kernel.model.Counter");
            userId = (counterRepo.findByName("com.liferay.counter.kernel.model.Counter").getCurrentid())-99;
            user.setUserid((int)userId);
            user.setEmailaddress(email);
            user.setCreatedate(LocalDateTime.now());
            user.setScreenname(cif);
            user.setFirstname(name);
            user.setMiddlename("");
            user.setLastname(name);
            userRepo.save(user);

            _log.info("User : ============ : "+user);

            //Adding NIYO USER
            counterRepo.incrementCounter("com.niyogin.service.onboarding.model.NiyoginUser");
            niyoUserId = (counterRepo.findByName("com.niyogin.service.onboarding.model.NiyoginUser").getCurrentid())-98;
            niyoUser.setNiyoUserId((int)niyoUserId);
            niyoUser.setUserType(3);
            niyoUser.setName(name);
            niyoUser.setUserId(user.getUserid());
            niyoUser.setAadharno(aadharNo);
            niyoUser.setPanno(panNo);
            niyoUser.setMobileno(mobileNo);
            niyoUser.setEmail(email);
            niyoUser.setCif(cif);

            _log.info("niyoUser > "+niyoUser);
            _log.info("Consent > "+consent);
            niyoUserRepo.save(niyoUser);
            _log.info("niyoUser > "+niyoUser);

            if(niyoUser!=null){
                _log.info("niyo_userId  : "+niyoUserId);

                //Adding Niyo User Details
                NiyoUserDetails niyoUserDetails = new NiyoUserDetails();

                counterRepo.incrementCounter("com.niyogin.service.onboarding.model.NiyoginUserDetails");
                long niyoUserDetailsId = (counterRepo.findByName("com.niyogin.service.onboarding.model.NiyoginUserDetails").getCurrentid())-98;
                niyoUserDetails.setNiyoUserDetailsId((int)niyoUserDetailsId);
                niyoUserDetails.setUserId(user.getUserid());
                niyoUserDetails.setNiyoUserId(niyoUser.getNiyoUserId());
                niyoUserDetails.setPassword(passsword);
                niyoUserDetails.setAccesscode(accessCode);
                niyoUserDetails.setNickName(nickName);
                niyoUserDetailsRepo.save(niyoUserDetails);
                _log.info("niyoginUserDetails  : "+niyoUserDetails);

                counterRepo.incrementCounter("com.niyogin.service.onboarding.model.NiyoginCustomerCompanyUser");
                niyoCustomerCompanyUserId = (counterRepo.findByName("com.niyogin.service.onboarding.model.NiyoginCustomerCompanyUser").getCurrentid())-99;
                niyoCustomerCompanyUser.setNiyoCustomerCompanyUserId((int)niyoCustomerCompanyUserId);
                _log.info("niyoCustomerCompanyUser : ============ : "+niyoCustomerCompanyUser);
                niyoCustomerCompanyUser.setNiyoUserId(niyoUser.getNiyoUserId());
                niyoCustomerCompanyUser.setNiyoCustomerCompanyId((int)niyoCustomerCompanyId);
                niyoCustomerCompanyUserRepo.save(niyoCustomerCompanyUser);

            }

        }catch (Exception e){
            _log.error("Erro Found : "+ e);
            niyoUserId = 0;
        }
        return String.valueOf(niyoUserId);
    }

}
