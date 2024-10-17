package com.niyogin.UserMigration.repo;

import com.niyogin.UserMigration.model.Counter;
import com.niyogin.UserMigration.model.NiyoUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiyoUserDetailsRepo  extends JpaRepository<NiyoUserDetails,Long> {
}
