package br.com.bragarodrigues.curriculo.service.security;

//Crir um SecurityService para fornecer o usuário conectado atual e o usuário de login automático após o registro
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
