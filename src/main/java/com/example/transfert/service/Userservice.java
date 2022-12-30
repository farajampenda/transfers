package com.example.transfert.service;

import com.example.transfert.dto.RoleDto;
import com.example.transfert.dto.UserDto;
import com.example.transfert.entity.Role;
import com.example.transfert.entity.User;
import com.example.transfert.repository.RoleRepository;
import com.example.transfert.repository.UserRepository;
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
public class Userservice {
    @Autowired
    private UserRepository useRep;

    @Autowired
    private RoleRepository roleRep;

    public RoleDto convertRoleToDto(Role role) {
        RoleDto dto = new RoleDto(role.getId(), role.getRolename());
        return dto;
    }

    public UserDto convertToDto(User user) {
        List<RoleDto> role = user.getRole().stream().map(this::convertRoleToDto).collect(Collectors.toList());
        UserDto dto = new UserDto(user.getId(), user.getNom(), user.getPrenom(), user.getUsername(), user.getPassword(), user.getDate(), role);
        return dto;
    }

    public User convertToEntity(UserDto use) {
        User entity = new User();
        entity.setNom(use.getName());
        entity.setPrenom(use.getLastname());
        entity.setPassword(use.getCode());
        return entity;
    }

    public ResponseEntity<?> selectUsers(Long id) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        try {
            List<UserDto> userList = useRep.findById(id).stream().map(this::convertToDto).collect(Collectors.toList());
            message.put("Status", 1);
            message.put("Datas", userList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.clear();
            message.put("Statut", 0);
            message.put("error", "pas de result");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }
    }

    public ResponseEntity<?> addUser(UserDto use) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();

        try {
            Role role = roleRep.findByRolename("client");
            User users = convertToEntity(use);
            users.setUsername(use.getName() + "" + use.getLastname());
            users.setDate(LocalDate.now());
            if (role != null) {
                users.getRole().add(role);
            } else {

                role = new Role("client", true);
                roleRep.save(role);
                users.getRole().add(role);
            }
            useRep.save(users);
            message.put("Status", 1);
            message.put("message", "Votre inscription viens d'être confirmé");
            message.put("info", "Veiller vous connectez maintenant");

            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.clear();
            message.put("Status", 0);
            message.put("error", "Verifier bien vos données");

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateUser(Long id, UserDto user) {
        Map<String, Object> message = new LinkedHashMap<String, Object>();
        User user1 = useRep.findById(id).get();
        try {
            if (user1 != null) {
                if (user.getName() != "") {
                    user1.setNom(user.getName());
                }
                if (user.getLastname() != "") {
                    user1.setPrenom(user.getLastname());
                }
                if (user.getUsers() != "") {
                    user1.setUsername(user.getUsers());
                }
                if (user.getCode() != "") {
                    user1.setPassword(user.getCode());
                }
            }
            useRep.save(user1);
            message.put("Statut", 1);
            message.put("message", "Votre compte à été modifier");
            message.put("Datas", user1);

            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.clear();
            message.put("Statut", 0);
            message.put("error", "Verifier bien vos données");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteUser(Long id) {
        Map<String, Object> message = new LinkedHashMap<>();
        User user = useRep.findById(id).get();
        try {
            if (user != null) {
                Role role = roleRep.findById(user.getRole().get(0).getId()).get();
                if (role != null) {
                    role.setValide(false);
                    roleRep.save(role);
                    message.put("Statut", 1);
                    message.put("message", "Vous venez de supprimer votre compte");
                }
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("Statut", 0);
            message.put("error", "Echec de supprission de votre compte");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
