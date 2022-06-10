package com.indatacore.restAPI.repositories;

import com.indatacore.restAPI.enumeration.EnumSex;
import com.indatacore.restAPI.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


    //--------------------------------------Bonus--------------------------------------
    //---------------------------------------------------------------------------------
    Client findByEmail(String email);
    @Query("SELECT e FROM Client e where e.isActive=true ")
    List<Client> ClientActive();
    @Query(value = "select * from CLIENT where SEX = :sex",  nativeQuery = true)
    List<Client> findClientBySex(@Param("sex") EnumSex sex);

}
