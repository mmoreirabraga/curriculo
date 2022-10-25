package br.com.bragarodrigues.curriculo.repository;

import br.com.bragarodrigues.curriculo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
