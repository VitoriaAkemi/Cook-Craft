package br.com.fiap.cookcraft.services;

import br.com.fiap.cookcraft.entities.User;
import br.com.fiap.cookcraft.entities.UserProfile;
import br.com.fiap.cookcraft.exception.UniqueViolationException;
import br.com.fiap.cookcraft.exception.EntityNotFoundException;
import br.com.fiap.cookcraft.repositories.UserProfileRepository;
import br.com.fiap.cookcraft.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueViolationException("Nome de usuário ou email já cadastrados");
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado com o id informado"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByUser(String user) {
        return repository.findByUser(user).orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado com o username informado"));
    }

    @Transactional(readOnly = true)
    public User.Role findRoleByUsername(String username) {
        return repository.findRoleByUser(username);
    }

    @Transactional
    public User setProfile(Long userId, Long profileId){
        User user = findById(userId);
        UserProfile profile = profileRepository.findById(profileId).orElseThrow(() -> new EntityNotFoundException("Nenhum profile encontrado para esse id"));
        user.getUserProfile().add(profile);

        return repository.save(user);
    }
}
