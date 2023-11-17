package br.com.fiap.cookcraft.controllers;

import br.com.fiap.cookcraft.dto.UserDTO;
import br.com.fiap.cookcraft.entities.User;
import br.com.fiap.cookcraft.services.UserProfileService;
import br.com.fiap.cookcraft.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    private ResponseEntity<UserDTO> create(@Valid @RequestBody User user){

        User userCreated = service.create(user);
        UserDTO dto = new UserDTO(userCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping
    private ResponseEntity<UserDTO> setProfile(@RequestParam Long userId, @RequestParam Long profileId){

        User user = service.setProfile(userId, profileId);
        UserDTO dto = new UserDTO(user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<UserDTO> findById(@PathVariable Long id){

        User user = service.findById(id);
        UserDTO dto = new UserDTO(user);

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/user/{user}")
    private  ResponseEntity<UserDTO> findByUser(@PathVariable(value = "user") String user){

        User userFinded = service.findByUser(user);
        UserDTO dto = new UserDTO(userFinded);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    private ResponseEntity<List<UserDTO>> findAll(){

        List<User> users = service.findAll();
        List<UserDTO> usersDTO = users.stream().map((user) -> new UserDTO(user)).toList();

        return ResponseEntity.ok(usersDTO);
    }
}
