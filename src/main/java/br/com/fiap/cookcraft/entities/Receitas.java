package br.com.fiap.cookcraft.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_RECEITAS")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tempo_preparo")
    private String tempoPreparo;

    @Column(name = "nome_receita")
    private String nomeReceita;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private UserProfile profile;

    @ManyToMany
    @JoinColumn(name = "ingrediente_id", referencedColumnName = "id")
    private List<Ingredientes> ingredientes;

    public Receitas(){}

    public Receitas(Long id, String tempoPreparo, String nomeReceita, UserProfile profile, List<Ingredientes> ingredientes) {
        this.id = id;
        this.tempoPreparo = tempoPreparo;
        this.nomeReceita = nomeReceita;
        this.profile = profile;
        this.ingredientes = ingredientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    @JsonIgnore
    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receitas receitas = (Receitas) o;
        return Objects.equals(id, receitas.id) && Objects.equals(tempoPreparo, receitas.tempoPreparo) && Objects.equals(nomeReceita, receitas.nomeReceita) && Objects.equals(profile, receitas.profile) && Objects.equals(ingredientes, receitas.ingredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tempoPreparo, nomeReceita, profile, ingredientes);
    }
}
