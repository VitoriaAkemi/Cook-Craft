package br.com.fiap.cookcraft.services;

import br.com.fiap.cookcraft.dto.UserProfileDTO;
import br.com.fiap.cookcraft.entities.UserProfile;
import br.com.fiap.cookcraft.exception.EntityNotFoundException;
import br.com.fiap.cookcraft.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Transactional
    public UserProfile create(UserProfile userProfile){
        return repository.save(userProfile);
    }

    @Transactional(readOnly = true)
    public List<UserProfile> findAllReceitas(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserProfileDTO> findAll(){
        List<UserProfile> profiles = repository.findAll();
        List<UserProfileDTO> profilesDTO = profiles.stream().map(profile -> new UserProfileDTO(profile)).toList();

        return profilesDTO;
    }

    @Transactional(readOnly = true)
    public UserProfile findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum perfil encontrado com esse id!"));
    }
}
