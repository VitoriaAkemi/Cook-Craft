package br.com.fiap.cookcraft.controllers;

import br.com.fiap.cookcraft.dto.UserDTO;
import br.com.fiap.cookcraft.entities.Ingredientes;
import br.com.fiap.cookcraft.entities.Receitas;
import br.com.fiap.cookcraft.entities.User;
import br.com.fiap.cookcraft.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping
    private ResponseEntity<List<Receitas>> findAll(){

        List<Receitas> receitas = service.findAll();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping(value = "/{nomeReceita}")
    private ResponseEntity<Receitas> findByNameReceita(@PathVariable(name = "nomeReceita") String nomeReceita){
        Receitas receita = service.findByNomeReceita(nomeReceita);
        return ResponseEntity.ok(receita);
    }

    @PostMapping
    private ResponseEntity<Receitas> create(@RequestBody Receitas receitas){

        Receitas receitaCreated = service.create(receitas);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaCreated);
    }

    @PutMapping(value = "/addIngredientes")
    private ResponseEntity<Receitas> addIngredientes(@RequestParam Long idReceita, @RequestBody List<Ingredientes> ingredientes){

        Receitas receitas = service.addIngredientes(idReceita, ingredientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitas);
    }

    @PostMapping(value = "/addIngredientesById")
    private ResponseEntity<Receitas> addIngredientesById(@RequestParam Long idReceita, @RequestParam Long idIngrediente){

        Receitas receitas = service.addIngredientesById(idIngrediente,idReceita);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitas);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
