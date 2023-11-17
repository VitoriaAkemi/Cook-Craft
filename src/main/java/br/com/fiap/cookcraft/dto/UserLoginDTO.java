package br.com.fiap.cookcraft.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

    @NotBlank(message = "Campo user não pode ser vazio")
    private String user;
    @NotBlank(message = "Campo password não pode ser vazio")
    private String password;

    public UserLoginDTO(){}

    public UserLoginDTO(String username, String password) {
        this.user = username;
        this.password = password;
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
}
