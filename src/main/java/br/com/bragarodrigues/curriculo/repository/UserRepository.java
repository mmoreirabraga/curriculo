package br.com.bragarodrigues.curriculo.repository;

import br.com.bragarodrigues.curriculo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
