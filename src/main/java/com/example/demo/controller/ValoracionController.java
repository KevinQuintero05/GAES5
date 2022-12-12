package com.example.demo.controller;

import com.example.demo.entity.Servicio;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Valoracion;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.IValoracionRepository;
import com.example.demo.repository.IservicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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

    @GetMapping("/valoraciones-usuarios/all")
    public String GetValoracionesUsuarios (Model model){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();

            List<Valoracion> valoracionList = iValoracionRepository.getValoracionByIdusuario(loginUser.getIdusuario());
            //List<Valoracion> valoracionList = iValoracionRepository.findAll();
            model.addAttribute("valoraciones", valoracionList);
            return "AtencionCliente/Valoraciones/Valoraciones-usuarios";
        } catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/valoraciones-usuarios/new")
    public String GetShowCreateValoracion(Model model){
        List<Servicio> servicios = iservicioRepository.findAll();
        List<Usuario> usuarios = iUsuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("servicios", servicios);
        model.addAttribute("valoracion", new Valoracion());
        return "AtencionCliente/Valoraciones/Create";
    }

    @PostMapping("/valoraciones-usuarios/save")
    public String SaveValoracion(@Valid Valoracion valoracion, BindingResult result,Model model){

        if (result.hasErrors()){
            List<Servicio> servicios = iservicioRepository.findAll();
            List<Usuario> usuarios = iUsuarioRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("servicios", servicios);
            return "AtencionCliente/Valoraciones/Create";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        valoracion.setUsuario(loginUser);
        iValoracionRepository.save(valoracion);
        return "redirect:/valoraciones-usuarios/all";
    }

    @GetMapping("/valoraciones-usuarios/edit/{id}")
    public String ShowUpdateValoracion(Model model, @PathVariable long id){
        Valoracion valoracionbd = iValoracionRepository.findById(id).get();
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        model.addAttribute("servicios", iservicioRepository.findAll());
        model.addAttribute("valoracion",valoracionbd);
        return "AtencionCliente/Valoraciones/Edit";
    }


    @PostMapping("/valoraciones-usuarios/update/{id}")
    public String updateValoracion(@PathVariable("id") long id, Valoracion valoracion, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        valoracion.setUsuario(loginUser);
        valoracion.setIdvaloracionservicio(id);
        iValoracionRepository.save(valoracion);
        return "redirect:/valoraciones-usuarios/all";
    }

    @GetMapping("/valoraciones-usuarios/delete/{id}")
    public String deleteValoracion(Model model,@PathVariable long id){
        iValoracionRepository.deleteById(id);
        return "redirect:/valoraciones-usuarios/all";
    }
}