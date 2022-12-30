package com.example.transfert.service;

import com.example.transfert.dto.CaisseDto;
import com.example.transfert.dto.CondeTransfert;
import com.example.transfert.dto.SommeClient;
import com.example.transfert.dto.SommeDepot;
import com.example.transfert.entity.Caisse;
import com.example.transfert.entity.Compte;
import com.example.transfert.repository.CaisseRepository;
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
import java.util.stream.Collectors;

@Service
public class Servicecaisse {
    @Autowired
    private CaisseRepository caisseRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CompteDepotRepository depotRepository;

    public Caisse ConvertyToEntity(CaisseDto dto) {
        Caisse caisse = new Caisse();
        caisse.setReceveur(dto.getNomRecevuer());
        caisse.setMontant(dto.getSomme());
        return caisse;
    }

    public String codeDeTransaction() {
        String codeEnvoie = "";
        String codeOriginal = "TRANS-5-5-60-MONY";
        CondeTransfert caisse = caisseRepository.findByCodedetransaction();
        if (caisse != null) {
            String code = caisse.getCode();

            String[] codeDbb = code.split("-");
            int code1 = Integer.valueOf(codeDbb[1]) + 1;
            int code2 = Integer.valueOf(codeDbb[2]) + 2;
            int code3 = Integer.valueOf(codeDbb[3]) + 1;

            codeEnvoie = "TRANS-" + code1 + "-" + code2 + "-" + code3 + "-MONY";
        } else {
            codeEnvoie = codeOriginal;
        }
        return codeEnvoie;
    }

    public Double ClientSommes(Long compteid) {
        Double caisse = 0.0;
        Double depots = 0.0;
        SommeClient somme = caisseRepository.sommeAllByCompte(compteid);

        if (somme != null) {
            caisse = somme.getSomme();
        }

        SommeDepot depot = depotRepository.sommeDepotAllByCompte(compteid);
        if (depot != null || depot.getSomme() != 0) {
            depots = depot.getSomme();
        }

        return depots - caisse;
    }

    public ResponseEntity<?> transfert(CaisseDto caisse) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Long compteID = Long.valueOf(caisse.getCompte());
            Compte compte = compteRepository.findById(compteID).get();
            Double sommetotalClient = ClientSommes(compteID);
            String codeTrans = codeDeTransaction();

            if (caisse.getSomme() < sommetotalClient) {
                Caisse caisseEntity = ConvertyToEntity(caisse);
                caisseEntity.setCompt(compte);
                caisseEntity.setValidation(false);
                caisseEntity.setTva(5);
                caisseEntity.setDate(LocalDate.now());
                caisseEntity.setFraistransfert((caisse.getSomme() * caisseEntity.getTva()) / 100);
                caisseEntity.setCodetansaction(codeTrans);

                caisseRepository.save(caisseEntity);

                message.put("Statut", 1);
                message.put("Somme", "vous avez envoyer " + caisse.getSomme());
                message.put("Receveur", caisse.getNomRecevuer());
                message.put("Code ", codeTrans);
                return new ResponseEntity<>(message, HttpStatus.OK);

            } else {
                message.put("Statut", 0);
                message.put("solde", "votre solde est de " + sommetotalClient);
                message.put("Somme", "Votre montant est superieur à votre solde");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("Error", "Echec d'envoie");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteService(Long caisseid) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Caisse caisse = caisseRepository.findById(caisseid).get();
            if (caisse.getValidation() == true) {
                message.put("Statut", 1);
                message.put("Response", "Imposible, le receveur à déjà retire");
            } else {
                caisseRepository.deleteById(caisseid);
                message.put("Statut", 1);
                message.put("Response", "vous venez d'annulé votre transfert");
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("Response", "Error 404");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> select(Long compteid) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            List<Caisse> caisse = caisseRepository.monCasse(compteid).stream().collect(Collectors.toList());
            message.put("Statut", 1);
            message.put("Datas", caisse);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("Response", "aucun transfert faite");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
