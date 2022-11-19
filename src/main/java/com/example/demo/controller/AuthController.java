package com.example.demo.controller;

import com.example.demo.Service.UsuarioService;
import com.example.demo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    @RequestMapping(value = {"/register"}, method =  RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("usuario", new Usuario());
        return "auth/register";
    }

    @RequestMapping(value = {"/register"}, method =  RequestMethod.POST)
    public String registerUser(Model model, @Valid Usuario usuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Usuario registrado");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = usuarioService.isUserPresent(usuario);
        if((Boolean) userPresentObj.get(0)){
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        usuarioService.saveUser(usuario);
        model.addAttribute("successMessage", "Usuario registrado con exito");

        return "auth/login";

    }
}
