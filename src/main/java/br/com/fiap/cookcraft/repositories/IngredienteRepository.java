package br.com.fiap.cookcraft.repositories;

import br.com.fiap.cookcraft.entities.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingredientes,Long> {
}
