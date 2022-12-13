package com.example.demo.controller;

import com.example.demo.entity.Pqrs;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.IPqrsRepository;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.IservicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PqrsController {

    @Autowired
    private IPqrsRepository iPqrsRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IservicioRepository iservicioRepository;

    @GetMapping("/pqrs/all")
    public String GetPqrs(Model model){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();

            List<Pqrs> pqrsList = iPqrsRepository.getPqrsByIdusuario(loginUser.getIdusuario());

            //List<Pqrs> pqrsList = iPqrsRepository.findAll();
            model.addAttribute("pqrsList", pqrsList);
            return "AtencionCliente/Pqrs/Pqrs";
        }catch (Exception ex){
            return "error";
        }
    }

    //ADMIN
    @GetMapping("/pqrs-admin/all")
    public String GetPqrsAdmin(Model model){

        try{
            List<Pqrs> pqrsList = iPqrsRepository.findAll();
            model.addAttribute("pqrsList", pqrsList);
            return "AtencionCliente/Pqrs/Pqrs-Admin";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/pqrs/new")
    public  String GetShowCreatePqrs(Model model ){
        List<Usuario> usuarios = iUsuarioRepository.findAll();
        List<Servicio> servicios = iservicioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("servicios", servicios);
        model.addAttribute("pqrs", new Pqrs());
        return "AtencionCliente/Pqrs/Create";
    }

    @PostMapping("/pqrs/save")
    public String SavePqrs(@Valid Pqrs pqrs, BindingResult result, Model model){

        if(result.hasErrors()){
            List<Usuario> usuarios = iUsuarioRepository.findAll();
            List<Servicio> servicios = iservicioRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("servicios", servicios);

            return "AtencionCliente/Pqrs/Create";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        pqrs.setUsuario(loginUser);

        pqrs.setEmail(loginUser.getEmail());

        //pqrs.setRespondidapor(loginUser.getNombres());

        iPqrsRepository.save(pqrs);
        return "redirect:/pqrs/all";
    }

    @GetMapping("/pqrs/edit/{id}")
    public String showUpdatePqrs(Model model, @PathVariable long id){
        Pqrs pqrsbd = iPqrsRepository.findById(id).get();
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        model.addAttribute("servicios", iservicioRepository.findAll());
        model.addAttribute("pqrs",pqrsbd);
        return "AtencionCliente/pqrs/edit";
    }
/*
    @GetMapping("/pqrs-admin/respond/{id}")
    public String showPqrsAdmin(Model model, @PathVariable long id){
        Pqrs pqrsbd = iPqrsRepository.findById(id).get();
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        model.addAttribute("pqrs",pqrsbd);
        return "AtencionCliente/pqrs/respond";
    }
*/
    @PostMapping("/pqrs/update/{id}")
    public String updatePqrs(@PathVariable("id") long id, Pqrs pqrs, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        pqrs.setUsuario(loginUser);

        pqrs.setEmail(loginUser.getEmail());

        pqrs.setNoregistro(id);
        iPqrsRepository.save(pqrs);
        return "redirect:/pqrs/all";
    }



    @GetMapping("/pqrs/delete/{id}")
    public String deletePqrs(Model model, @PathVariable long id){
        try{
            iPqrsRepository.deleteById(id);
            return "redirect:/pqrs/all";
        }catch (Exception exception){
            return "redirect:/pqrs/all";
        }

    }
}
