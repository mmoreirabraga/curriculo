package br.com.bragarodrigues.curriculo.service;

import br.com.bragarodrigues.curriculo.model.Role;
import br.com.bragarodrigues.curriculo.model.User;
import br.com.bragarodrigues.curriculo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired// injeção de dependencia reduzindo código
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true) // Essa operação não realizará escrita ou alterações, apenas leitura.
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if(user == null) throw  new UsernameNotFoundException(username);// se Username for null, retornará uma exception de usuário null

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();// Como o metodo é só para leitura, usei a interface set para buscar rápido

        for(Role role : user.getRoles()){ // for percorrendo para cada usuário cadastrado na tabela roles
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); // a cada usuário encontrado ele adicionará para interface autoridade
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
