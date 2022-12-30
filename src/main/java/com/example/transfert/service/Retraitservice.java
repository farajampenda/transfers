package com.example.transfert.service;

import com.example.transfert.dto.RetraitDto;
import com.example.transfert.entity.Agent;
import com.example.transfert.entity.Caisse;
import com.example.transfert.entity.Retrait;
import com.example.transfert.repository.AgentRepository;
import com.example.transfert.repository.CaisseRepository;
import com.example.transfert.repository.RetraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class Retraitservice {

    @Autowired
    private RetraitRepository retraitRepository;
    @Autowired
    private CaisseRepository caisseRepository;
    @Autowired
    private AgentRepository agentRepository;

    public Retrait convertEntity(RetraitDto dto) {
        Retrait retrait = new Retrait();
        retrait.setCodetransaction(dto.getCodeDetransation());
        return retrait;
    }

    public ResponseEntity<?> retrait(Long id, RetraitDto dto) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Retrait retrait = new Retrait();

            Agent agent = agentRepository.findById(id).get();
            Caisse caisse = caisseRepository.findByCodetansaction(dto.getCodeDetransation());
            if (caisse.getValidation() == false) {
                retrait.setAgent(agent);
                retrait.setCaisse(caisse);
                retrait.setCodetransaction(dto.getCodeDetransation());
                retrait.setMontantcaisse(caisse.getMontant());
                retrait.setTva(1);
                retrait.setMontantretrait((caisse.getMontant() * 1) / 100);
                retrait.setDate(LocalDate.now());
                caisse.setValidation(true);
                caisseRepository.save(caisse);

                retraitRepository.save(retrait);

                message.put("Status", 1);
                message.put("message", "Retrait avec succès");
                message.put("data", retrait);

            } else {
                message.put("Status", 1);
                message.put("message", "code error");
            }

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "echec d'enregistrement");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> listRetrait(Long agentID) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Retrait retrait = new Retrait();

            Agent agent = agentRepository.findById(agentID).get();
            if (agent != null) {
                retrait = retraitRepository.findById(agentID).get();
                message.put("Status", 1);
                message.put("message", retrait);
            }

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "Pas d'info");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
