package com.example.transfert.dto;

import com.example.transfert.entity.Agent;

public class AgentDto {
    private Long id;
    private String nomAgent;
    private String prenomAgent;
    private String password;
    private String usernameAgent;
    private String image;

    public AgentDto() {

    }

    public AgentDto(String nomAgent, String prenomAgent, String password, String usernameAgent, String image) {
        this.nomAgent = nomAgent;
        this.prenomAgent = prenomAgent;
        this.password = password;
        this.usernameAgent = usernameAgent;
        this.image = image;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getPrenomAgent() {
        return prenomAgent;
    }

    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameAgent() {
        return usernameAgent;
    }

    public void setUsernameAgent(String usernameAgent) {
        this.usernameAgent = usernameAgent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Agent convertToEntity(AgentDto dto) {
        Agent agent = new Agent();
        agent.setNom(dto.getNomAgent());
        agent.setPrenom(dto.getPrenomAgent());
        agent.setUsername(dto.getUsernameAgent());
        agent.setPassword(dto.getPassword());
        agent.setImage("");
        return agent;
    }

    public AgentDto convertToDto(Agent entity) {
        AgentDto agent = new AgentDto();
        agent.setNomAgent(entity.getNom());
        agent.setPrenomAgent(entity.getPrenom());
        agent.setUsernameAgent(entity.getUsername());
        agent.setPassword(entity.getPassword());
        agent.setImage("");
        return agent;
    }
}
