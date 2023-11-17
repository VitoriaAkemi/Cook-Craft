package br.com.fiap.cookcraft.dto;

import br.com.fiap.cookcraft.entities.User;
import br.com.fiap.cookcraft.entities.UserProfile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String role;
    private List<UserProfileDTO> userProfile;

    public UserDTO(){}

    public UserDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.user = user.getUser();
        this.role = user.getRole().name().substring("ROLE_".length());
        this.userProfile = user.getUserProfile().stream().map(profile -> new UserProfileDTO(profile)).collect(Collectors.toList());
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserProfileDTO> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfileDTO> userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(user, userDTO.user) && Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, user, role);
    }
}
