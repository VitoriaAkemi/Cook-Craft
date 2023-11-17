package br.com.fiap.cookcraft.services;

import br.com.fiap.cookcraft.entities.Ingredientes;
import br.com.fiap.cookcraft.exception.EntityNotFoundException;
import br.com.fiap.cookcraft.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repository;

    @Transactional
    public Ingredientes create(Ingredientes ingredientes) {
        return repository.save(ingredientes);
    }

    @Transactional(readOnly = true)
    public List<Ingredientes> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Ingredientes findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ingrediente nao existente para o id especificado"));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
