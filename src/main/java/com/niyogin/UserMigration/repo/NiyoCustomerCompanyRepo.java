package com.niyogin.UserMigration.repo;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface NiyoCustomerCompanyRepo extends JpaRepository<NiyoCustomerCompany,Long> {
    @Query("SELECT u FROM NiyoCustomerCompany u")
    List<NiyoCustomerCompany> getEmpList();

    @Query("SELECT U FROM NiyoCustomerCompany U WHERE U.niyoCustomerCompanyId =:n")
    NiyoCustomerCompany getCustomerCompanyByCustomerId(@Param("n") Long niyoCustomerCompanyId);

    @Query("SELECT c FROM NiyoCustomerCompany c WHERE c.email =:email")
    NiyoCustomerCompany findByEmail(@Param("email") String email);

//    @Modifying
//    @Query("DELETE FROM NiyoCustomerCompany U WHERE U.niyoCustomerCompanyId = :n")
//    int deleteCustomerById(@Param("n") Long niyoCustomerCompanyId);

}

