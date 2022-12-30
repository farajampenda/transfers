package com.example.transfert.repository;

import com.example.transfert.dto.DepotDescrition;
import com.example.transfert.dto.SommeDepot;
import com.example.transfert.entity.Comptedepot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteDepotRepository extends JpaRepository<Comptedepot, Long> {
    @Query(value = "select u.id as 'user',d.description as 'description',c.id as 'compteid' from  comptedepot d,user u, compte c where d.compte_id=c.id and c.user_id=u.id and c.id=?1 order by d.compte_id desc", nativeQuery = true)
    List<DepotDescrition> description(Long compteId);

    @Query(value = "select sum(c.montant) as 'somme' from comptedepot c where compte_id=?1", nativeQuery = true)
    public SommeDepot sommeDepotAllByCompte(Long comptid);
}
