package com.example.transfert.dto;


public class CompteDto {
    private Long id;
    private String device;
    private UserDto client;

    public CompteDto(String device, UserDto client) {
        this.device = device;
        this.client = client;
    }

    public CompteDto(String device) {
        this.device = device;
    }

    public CompteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public UserDto getClient() {
        return client;
    }

    public void setClient(UserDto client) {
        this.client = client;
    }
}
