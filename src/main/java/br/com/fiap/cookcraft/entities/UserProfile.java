package br.com.fiap.cookcraft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_name")
    private String profileName;

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference
    private List<Receitas> receitas;

    public UserProfile(){}
    public UserProfile(Long id, String profileName, List<Receitas> receitas) {
        this.id = id;
        this.profileName = profileName;
        this.receitas = receitas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<Receitas> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receitas> receitas) {
        this.receitas = receitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(profileName, that.profileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileName);
    }
}
