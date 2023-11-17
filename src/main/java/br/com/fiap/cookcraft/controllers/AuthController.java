package br.com.fiap.cookcraft.controllers;

import br.com.fiap.cookcraft.dto.UserLoginDTO;
import br.com.fiap.cookcraft.exception.ErrorMessage;
import br.com.fiap.cookcraft.jwt.JwtToken;
import br.com.fiap.cookcraft.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class AuthController {

    private final JwtUserDetailsService jwtService;
    private final AuthenticationManager authManager;

    public AuthController(JwtUserDetailsService jwtService, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@Valid @RequestBody UserLoginDTO loginDTO, HttpServletRequest request) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPassword());
            authManager.authenticate(authenticationToken);
            JwtToken tokenAuthenticated = jwtService.getTokenAuthenticated(loginDTO.getUser());

            return ResponseEntity.ok(tokenAuthenticated);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais incorretas"));
    }

}
