package br.com.fiap.cookcraft.repositories;

import br.com.fiap.cookcraft.entities.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receitas,Long> {

    @Query("select r from Receitas r where UPPER(r.nomeReceita)  like UPPER(CONCAT('%',:nomeReceita,'%'))")
    Optional<Receitas> findByNomeReceitaLike(@Param("nomeReceita") String nomeReceita);
}
