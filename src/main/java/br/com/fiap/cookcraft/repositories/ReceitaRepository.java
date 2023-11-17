package br.com.fiap.cookcraft.repositories;

import br.com.fiap.cookcraft.entities.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receitas,Long> {

    Optional<Receitas> findByNomeReceita(String nomeReceita);
}
