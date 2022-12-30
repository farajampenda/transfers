package com.example.transfert.service;

import com.example.transfert.dto.AgentDto;
import com.example.transfert.entity.Agent;
import com.example.transfert.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Agentservice {

    @Autowired
    private AgentRepository agentRepository;

    private AgentDto agentDto;


    public AgentDto convertDto(Agent entity) {

        AgentDto agent = new AgentDto();
        agent.setNomAgent(entity.getNom());
        agent.setPrenomAgent(entity.getPrenom());
        agent.setUsernameAgent(entity.getUsername());
        agent.setPassword(entity.getPassword());
        return agent;
        // return  agentDto.getInstance().convertToDto(agent);
    }

    public ResponseEntity<?> add(AgentDto agentDto) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Agent agent = agentDto.convertToEntity(agentDto);
            agentRepository.save(agent);
            message.put("Status", 1);
            message.put("message", "enregistrement avec succès");

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "echec d'enregistrement");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> allAgent() {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            List<AgentDto> agents = agentRepository.findAll().stream().map(this::convertDto).collect(Collectors.toList());
            message.put("Status", 1);
            message.put("data", agents);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "pas d'info");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> agentOne(Long agnetID) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Agent agent = agentRepository.findById(agnetID).get();
            AgentDto agentdto = convertDto(agent);
            message.put("Status", 1);
            message.put("data", agentdto);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "cet agent n'existe pas!");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(Long agnetID) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Agent agent = agentRepository.findById(agnetID).get();
            if (agent != null) {
                agentRepository.delete(agent);
                message.put("Status", 1);
                message.put("message", "suppression avec succès!");

            } else {
                message.put("Status", 0);
                message.put("message", "cet compte est faux");
            }

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "échec de suppression");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> update(Long agnetID, AgentDto agentdto) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            Agent agent = agentRepository.findById(agnetID).get();
            if (agent != null) {
                if (agentDto.getNomAgent() != null && agentdto.getNomAgent() != "") {
                    agent.setNom(agentDto.getNomAgent());
                }
                if (agentDto.getPrenomAgent() != null && agentdto.getPrenomAgent() != "") {
                    agent.setPrenom(agentDto.getPrenomAgent());
                }

                if (agentDto.getPassword() != null && agentdto.getPassword() != "") {
                    agent.setPassword(agentDto.getPassword());
                }
                if (agentDto.getImage() != null && agentdto.getImage() != "") {
                    agent.setImage(agentDto.getImage());
                }
                if (agentDto.getNomAgent() != null && agentdto.getUsernameAgent() != "") {
                    agent.setUsername(agentdto.getUsernameAgent());
                }
                agentRepository.save(agent);
                message.put("Status", 1);
                message.put("message", "modification reussi");
                message.put("data", agent);
            } else {
                message.put("Status", 0);
                message.put("message", "verifier bien vos données");
            }
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("Status", 0);
            message.put("message", "Echec de modification");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
