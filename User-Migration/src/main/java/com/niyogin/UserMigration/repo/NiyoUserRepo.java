package com.niyogin.UserMigration.repo;

import com.niyogin.UserMigration.model.Counter;
import com.niyogin.UserMigration.model.NiyoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NiyoUserRepo extends JpaRepository<NiyoUser,Long> {

    @Query("SELECT c FROM NiyoUser c WHERE c.email =:email")
    String findByEmail(@Param("email") String email);
}
