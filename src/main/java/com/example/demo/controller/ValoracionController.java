package com.example.demo.controller;

import com.example.demo.entity.Servicio;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Valoracion;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.IValoracionRepository;
import com.example.demo.repository.IservicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ValoracionController {

    @Autowired
    private IValoracionRepository iValoracionRepository;

    @Autowired
    private IservicioRepository iservicioRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @GetMapping("/valoraciones/all")
    public String GetValoraciones (Model model){
        try{
           List<Valoracion> valoracionList = iValoracionRepository.findAll();
           model.addAttribute("valoraciones", valoracionList);
           return "AtencionCliente/Valoraciones/Valoraciones";
        } catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/valoraciones/new")
    public String GetShowCreateValoracion(Model model){
        List<Servicio> servicios = iservicioRepository.findAll();
        List<Usuario> usuarios = iUsuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("servicios", servicios);
        model.addAttribute("valoracion", new Valoracion());
        return "AtencionCliente/Valoraciones/Create";
    }

    @PostMapping("/valoraciones/save")
    public String SaveValoracion(Valoracion valoracion){
        iValoracionRepository.save(valoracion);
        return "redirect:/valoraciones/all";
    }

    @GetMapping("/valoraciones/edit/{id}")
    public String ShowUpdateValoracion(Model model, @PathVariable long id){
        Valoracion valoracionbd = iValoracionRepository.findById(id).get();
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        model.addAttribute("servicios", iservicioRepository.findAll());
        model.addAttribute("valoracion",valoracionbd);
        return "AtencionCliente/Valoraciones/Edit";
    }


    @PostMapping("/valoraciones/update/{id}")
    public String updateValoracion(@PathVariable("id") long id, Valoracion valoracion, Model model){
        valoracion.setIdvaloracionservicio(id);
        iValoracionRepository.save(valoracion);
        return "redirect:/valoraciones/all";
    }

    @GetMapping("/valoraciones/delete/{id}")
    public String deleteValoracion(Model model,@PathVariable long id){
        iValoracionRepository.deleteById(id);
        return "redirect:/valoraciones/all";
    }
}
