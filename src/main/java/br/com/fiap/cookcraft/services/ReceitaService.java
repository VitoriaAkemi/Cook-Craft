package br.com.fiap.cookcraft.services;

import br.com.fiap.cookcraft.entities.Ingredientes;
import br.com.fiap.cookcraft.entities.Receitas;
import br.com.fiap.cookcraft.exception.EntityNotFoundException;
import br.com.fiap.cookcraft.repositories.IngredienteRepository;
import br.com.fiap.cookcraft.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional
    public Receitas create(Receitas receitas) {
        return repository.save(receitas);
    }

    @Transactional(readOnly = true)
    public List<Receitas> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Receitas findByNomeReceita(String nomeReceita) {
        return repository.findByNomeReceita(nomeReceita).orElseThrow(() -> new EntityNotFoundException("Receita não encontrada"));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Receitas addIngredientesById(Long idIngrediente, Long idReceita) {
        Ingredientes ingredientes = ingredienteRepository.findById(idIngrediente).
                orElseThrow(() -> new EntityNotFoundException("Nenhum ingrediente encontrado com esse id"));

        Receitas receitas = repository.findById(idReceita).
                orElseThrow(() -> new EntityNotFoundException("Nenhuma receita encontrada com esse id"));

        receitas.getIngredientes().add(ingredientes);
        return repository.save(receitas);
    }

    @Transactional
    public Receitas addIngredientes(Long idReceita, List<Ingredientes> ingredientes) {
        Receitas receitas = repository.findById(idReceita).
                orElseThrow(() -> new EntityNotFoundException("Nenhuma receita encontrada com esse id"));


        List<Ingredientes> ingredientesSaved = ingredienteRepository.saveAll(ingredientes);

        for (Ingredientes ingrediente:ingredientesSaved){
            receitas.getIngredientes().add(ingrediente);
        }

        return repository.save(receitas);
    }
}
