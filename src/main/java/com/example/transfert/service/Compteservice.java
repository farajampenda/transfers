package com.example.transfert.service;

import com.example.transfert.dto.CompteDto;
import com.example.transfert.dto.CompteType;
import com.example.transfert.dto.SommeClient;
import com.example.transfert.dto.SommeDepot;
import com.example.transfert.entity.Compte;
import com.example.transfert.entity.User;
import com.example.transfert.repository.CaisseRepository;
import com.example.transfert.repository.CompteDepotRepository;
import com.example.transfert.repository.CompteRepository;
import com.example.transfert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class Compteservice {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private CompteRepository compteRep;

    @Autowired
    private CaisseRepository caisseRepository;
    @Autowired
    private CompteDepotRepository depotRepository;

    public Compte convertToEntity(CompteDto compteDto) {
        Compte compte = new Compte(compteDto.getDevice());
        return compte;
    }


    public ResponseEntity<?> monCompte(Long userId) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            List<CompteType> compte = compteRep.findcompteuser(userId);
            message.put("Status", 1);
            message.put("Datas", compte);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Status", 0);
            message.put("error", "Pas de compte, Creer un compte");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> addCompte(CompteDto compte, Long id) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();

        try {
            User user = userRep.findById(id).get();
            Compte compt = convertToEntity(compte);
            compt.setUser(user);
            compteRep.save(compt);
            message.put("Status", 1);
            message.put("Datas", "Félicitation votre compte transmoney à été creer");
            message.put("Datas", "Le compte creer est en " + compt.getCurrency());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Status", 0);
            message.put("error", "Echec, ressayer encore");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
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


    public ResponseEntity<?> monSolde(Long compteID) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Double solde = ClientSommes(compteID);
            message.put("Status", 1);
            message.put("Datas", solde);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Status", 0);
            message.put("error", "0.00");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }
}
