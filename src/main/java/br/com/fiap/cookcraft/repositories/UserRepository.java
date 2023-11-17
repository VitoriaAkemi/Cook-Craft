package br.com.fiap.cookcraft.repositories;

import br.com.fiap.cookcraft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUser(String user);
    Optional<User> findByUserAndPassword(String user, String password);

    @Query("select u.role from User u where u.user like :username")
    User.Role findRoleByUser(String username);
}
