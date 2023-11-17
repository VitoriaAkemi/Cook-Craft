package br.com.fiap.cookcraft.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class JwtUserDetails extends User {

    private br.com.fiap.cookcraft.entities.User user;
    public JwtUserDetails(br.com.fiap.cookcraft.entities.User user) {
        super(user.getUser(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public Long getId(){
        return this.user.getId();
    }

    public String getRole(){
        return this.user.getRole().name();
    }

}
