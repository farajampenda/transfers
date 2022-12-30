package com.example.transfert.repository;

import com.example.transfert.dto.CondeTransfert;
import com.example.transfert.dto.SommeClient;
import com.example.transfert.entity.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    @Query(value = "select sum(c.montant) as 'somme' from  caisse c where c.compt_id=?1", nativeQuery = true)
    public SommeClient sommeAllByCompte(Long compteId);

    @Query(value = " select c.codetansaction as 'code' from caisse c order by c.id desc limit 0,1 ", nativeQuery = true)
    public CondeTransfert findByCodedetransaction();

    @Query(value = " select * from caisse c where c.compt_id=?1 group by compt_id order by id desc ", nativeQuery = true)
    List<Caisse> monCasse(Long compteid);

    Caisse findByCodetansaction(String codeDetransation);
}
