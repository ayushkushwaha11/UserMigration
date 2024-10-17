package com.niyogin.UserMigration.repo;

import com.niyogin.UserMigration.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepo extends JpaRepository<Counter,Long> {
    @Modifying
    @Query("UPDATE Counter c SET c.currentid = c.currentid + 100 WHERE c.name = :name")
    int incrementCounter(@Param("name") String name);

    @Query("SELECT c FROM Counter c WHERE c.name =:name")
    Counter findByName(@Param("name") String name);


}
