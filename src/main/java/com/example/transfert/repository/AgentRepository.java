package com.example.transfert.repository;

import com.example.transfert.entity.Agent;
import com.example.transfert.entity.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}