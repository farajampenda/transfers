package com.example.transfert.repository;

import com.example.transfert.dto.CompteType;
import com.example.transfert.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    @Query(value = "select c.id as 'compteid',c.currency as 'device',c.user_id as 'userID' from  compte c where  user_id=?1 ", nativeQuery = true)
    List<CompteType> findcompteuser(Long userId);
}
