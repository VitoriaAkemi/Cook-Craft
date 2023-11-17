package br.com.fiap.cookcraft.dto;

import br.com.fiap.cookcraft.entities.UserProfile;

public class UserProfileDTO {

    private Long id;
    private String profileName;

    public UserProfileDTO(){}

    public UserProfileDTO(UserProfile profile){
        this.id = profile.getId();
        this.profileName = profile.getProfileName();
    }
    public UserProfileDTO(Long id, String profileName) {
        this.id = id;
        this.profileName = profileName;
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
}
