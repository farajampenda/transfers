package com.example.transfert.repository;

import com.example.transfert.entity.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetraitRepository extends JpaRepository<Retrait, Long> {
}