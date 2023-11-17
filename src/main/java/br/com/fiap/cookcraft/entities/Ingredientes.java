package br.com.fiap.cookcraft.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_INGREDIENTES")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_ingrediente")
    private String nomeIngrediente;

    @Column(name = "quantidade")
    private String quantidade;

    public Ingredientes(){}
    public Ingredientes(Long id, String nomeIngrediente, String quantidade) {
        this.id = id;
        this.nomeIngrediente = nomeIngrediente;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredientes that = (Ingredientes) o;
        return Objects.equals(id, that.id) && Objects.equals(nomeIngrediente, that.nomeIngrediente) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeIngrediente, quantidade);
    }
}
