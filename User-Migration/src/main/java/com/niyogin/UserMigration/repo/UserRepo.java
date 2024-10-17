package com.niyogin.UserMigration.repo;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;
import com.niyogin.UserMigration.model.NiyoUser;
import com.niyogin.UserMigration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT c FROM User c WHERE c.emailaddress =:emailaddress")
    User findByEmail(@Param("emailaddress") String emailaddress);

}
