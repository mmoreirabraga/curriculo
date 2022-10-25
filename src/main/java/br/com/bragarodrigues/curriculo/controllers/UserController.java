package br.com.bragarodrigues.curriculo.controllers;

import br.com.bragarodrigues.curriculo.model.User;
import br.com.bragarodrigues.curriculo.service.security.SecurityService;
import br.com.bragarodrigues.curriculo.service.security.UserService;
import br.com.bragarodrigues.curriculo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("userForm", new User());

        return "registrado";
    }

    @PostMapping("/registro")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "registrado";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirecionado:/Bem vindo";
    }

    @GetMapping({"/", "/bemvindo"})
    public String welcome(Model model) {
        return "BemVindo";
    }
}
