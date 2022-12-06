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
public class AdminController {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminHome(Model model){
        List<Usuario> usuarioList = iUsuarioRepository.findAll();
        model.addAttribute("usuarioList", usuarioList);
        return "admin/dashboard";
    }

}
