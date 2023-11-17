package br.com.fiap.cookcraft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Campo user não pode ser vazio")
    @Column(name = "user_login", nullable = false, unique = true)
    private String user;

    @NotBlank(message = "Campo password não pode ser vazio")
    @Column(name = "password", nullable = false)
    private String password;
    @NotBlank(message = "Campo firstName não pode ser vazio")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Campo lastName não pode ser vazio")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Campo email não pode ser nulo ou vazio")
    @Email(message = "Formato de email inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.ROLE_CLIENTE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private List<UserProfile> userProfile;

    public enum Role{
        ROLE_CLIENTE
    }

    public User(){
    }

    public User(String user, String password, String firstName, String lastName, String email, List<UserProfile> userProfile) {
        this.user = user;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userProfile = userProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public List<UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfile> userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(id, user1.id) && Objects.equals(user, user1.user) && Objects.equals(password, user1.password) && Objects.equals(firstName, user1.firstName) && Objects.equals(lastName, user1.lastName) && Objects.equals(email, user1.email) && role == user1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, password, firstName, lastName, email, role);
    }
}
