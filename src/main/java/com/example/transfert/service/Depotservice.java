package com.example.transfert.service;

import com.example.transfert.dto.ComptedepotDto;
import com.example.transfert.dto.DepotDescrition;
import com.example.transfert.entity.Compte;
import com.example.transfert.entity.Comptedepot;
import com.example.transfert.repository.CompteDepotRepository;
import com.example.transfert.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class Depotservice {
    @Autowired
    private CompteDepotRepository depotRep;
    @Autowired
    private CompteRepository compteRep;

    public Comptedepot convertToEntity(ComptedepotDto dto) {
        Comptedepot depot = new Comptedepot();
        depot.setMontant(dto.getMontant());
        return depot;
    }

    public ResponseEntity<?> depot(ComptedepotDto depos) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Long comptId = Long.valueOf(depos.getCompteid());
            Compte compte = compteRep.findById(comptId).get();
            Comptedepot depots = convertToEntity(depos);
            depots.setDate(LocalDate.now());
            depots.setDescription("Vous avez déposé " + depots.getMontant() + " " + compte.getCurrency() + " le " + depots.getDate());
            depots.setCompte(compte);
            depotRep.save(depots);
            message.put("Statut", 1);
            message.put("message", "Vous venez de deposer une somme de" + depos.getMontant());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("message", "pas d'information de votre carte visa");
            message.put("error", "verifier bien votre compte");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> description(Long compteId) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            List<DepotDescrition> description = depotRep.description(compteId);
            message.put("Statut", 1);
            message.put("Historique", description);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("message", "vous n'avez rien deposer sur cette compte");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
