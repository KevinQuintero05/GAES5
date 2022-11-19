package com.example.demo.controller;

import com.example.demo.entity.Pqrs;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.IPqrsRepository;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PqrsController {

    @Autowired
    private IPqrsRepository iPqrsRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @GetMapping("/pqrs/all")
    public String GetPqrs(Model model){

        try{
            List<Pqrs> pqrsList = iPqrsRepository.findAll();
            model.addAttribute("pqrsList", pqrsList);
            return "AtencionCliente/Pqrs/Pqrs";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/pqrs/new")
    public  String GetShowCreatePqrs(Model model){
        List<Usuario> usuarios = iUsuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("pqrs", new Pqrs());
        return "AtencionCliente/Pqrs/Create";
    }

    @PostMapping("/pqrs/save")
    public String SavePqrs(Pqrs pqrs){
        iPqrsRepository.save(pqrs);
        return "redirect:/pqrs/all";
    }

    @GetMapping("/pqrs/edit/{id}")
    public String showUpdatePqrs(Model model, @PathVariable long id){
        Pqrs pqrsbd = iPqrsRepository.findById(id).get();
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        model.addAttribute("pqrs",pqrsbd);
        return "AtencionCliente/pqrs/edit";
    }

    @PostMapping("/pqrs/update/{id}")
    public String updatePqrs(@PathVariable("id") long id, Pqrs pqrs, Model model){
        pqrs.setNoregistro(id);
        iPqrsRepository.save(pqrs);
        return "redirect:/pqrs/all";
    }

    @GetMapping("/pqrs/delete/{id}")
    public String deletePqrs(Model model, @PathVariable long id){
        iPqrsRepository.deleteById(id);
        return "redirect:/pqrs/all";
    }
}
