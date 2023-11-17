package br.com.fiap.cookcraft.controllers;


import br.com.fiap.cookcraft.entities.UserProfile;
import br.com.fiap.cookcraft.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/profiles")
public class UserProfileController {

    @Autowired
    UserProfileService service;

    @PostMapping
    private ResponseEntity<UserProfile> create(@RequestBody UserProfile profile){

        UserProfile userProfile = service.create(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfile);
    }

    @GetMapping
    private ResponseEntity<List<UserProfile>> findAll(){
        List<UserProfile> profiles = service.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(value ="/{id}")
    private ResponseEntity<UserProfile> findById(@PathVariable Long id){
        UserProfile profile = service.findById(id);
        return ResponseEntity.ok(profile);
    }
}
