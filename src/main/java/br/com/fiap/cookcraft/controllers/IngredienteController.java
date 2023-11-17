package br.com.fiap.cookcraft.controllers;

import br.com.fiap.cookcraft.entities.Ingredientes;
import br.com.fiap.cookcraft.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService service;

    @PostMapping
    public ResponseEntity<Ingredientes> create(@RequestBody Ingredientes ingredientes){
        Ingredientes ingredientesCreated = service.create(ingredientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientesCreated);
    }

    @GetMapping
    public ResponseEntity<List<Ingredientes>> findAll(){
        List<Ingredientes> all = service.findAll();
        return  ResponseEntity.ok(all);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredientes> findById(@PathVariable Long id){
        Ingredientes ingrediente = service.findById(id);
        return ResponseEntity.ok(ingrediente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
