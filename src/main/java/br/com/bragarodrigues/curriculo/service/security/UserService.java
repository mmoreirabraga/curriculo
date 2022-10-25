package br.com.bragarodrigues.curriculo.service.security;

import br.com.bragarodrigues.curriculo.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
