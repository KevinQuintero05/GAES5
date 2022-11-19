package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UsuarioController {

    /*@Autowired
    private IUsuarioRepository iUsuarioRepository;

    @GetMapping("users")
    public String GetUsers(Model model){
        try {
            List<Usuario> usuarios= iUsuarioRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            return "Usuarios/Usuarios";
        } catch (Exception ex){
            return "Error";
        }
    }

    @GetMapping("/users/new")
    private String GetShowCreateUser(Model model){
        model.addAttribute("usuario", new Usuario());
        return "Usuarios/Create";
    }*/

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "usuario/dashboard";
    }
}
