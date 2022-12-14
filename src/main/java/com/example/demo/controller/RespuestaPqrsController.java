package com.example.demo.controller;

import com.example.demo.entity.Pqrs;
import com.example.demo.entity.RespuestaPqrs;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.IPqrsRepository;
import com.example.demo.repository.IRespuestaPqrsRepository;
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
public class RespuestaPqrsController {

    @Autowired
    private IPqrsRepository iPqrsRepository;

    @Autowired
    private IRespuestaPqrsRepository iRespuestaPqrsRepository;

    @GetMapping("/respuestas/all")
    public String GetRespuestas(Model model){
        try{
            List<RespuestaPqrs> respuestasPqrs = iRespuestaPqrsRepository.findAll();
            model.addAttribute("respuestasPqrs", respuestasPqrs);
            return "AtencionCliente/RespuestasPqrs/Respuestas";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/respuestas-usuario/all")
    public String GetRespuestasUsuario(Model model){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();
            List<RespuestaPqrs> respuestasPqrs = iRespuestaPqrsRepository.getRespuestaByidusuario(loginUser.getIdusuario());
            model.addAttribute("respuestasPqrs", respuestasPqrs);
            return "AtencionCliente/RespuestasPqrs/Respuestas-usuario";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/respuestas/new")
    public String GetShowCreateRespuesta(Model model){
        List<Pqrs> pqrsList = iPqrsRepository.findAll();
        model.addAttribute("pqrsList", pqrsList);
        model.addAttribute("respuesta", new RespuestaPqrs());
        return "AtencionCliente/RespuestasPqrs/Create";
    }
    /*
    @GetMapping("/respuestas/new/{id}" )
    public String GetShowCreateRespuestaByid( Model model,  @PathVariable long id){
        Pqrs pqrsbd = iPqrsRepository.findById(id).get();
        model.addAttribute("pqrs",pqrsbd);
        List<Pqrs> pqrsList = iPqrsRepository.findAll();
        model.addAttribute("pqrsList", pqrsList);
        model.addAttribute("respuesta", new RespuestaPqrs());
        return "AtencionCliente/RespuestasPqrs/Create";
    }*/
    @PostMapping("/respuestas/save")
    public String SaveRespuesta(@Valid RespuestaPqrs respuestaPqrs, BindingResult result, Model model){
        List<Pqrs> pqrsList = iPqrsRepository.findAll();
        model.addAttribute("pqrsList", pqrsList);
        if (result.hasErrors()){

            return "AtencionCliente/RespuestasPqrs/Create";
        }
        iRespuestaPqrsRepository.save(respuestaPqrs);
        return "redirect:/respuestas/all";
    }

    @GetMapping("/respuestas/edit/{id}")
    public String ShowUpdateRespuesta(Model model, @PathVariable long id){
        RespuestaPqrs respuestabd = iRespuestaPqrsRepository.findById(id).get();
        model.addAttribute("pqrsList", iPqrsRepository.findAll());
        model.addAttribute("respuesta", respuestabd);
        return "AtencionCliente/RespuestasPqrs/Edit";
    }

    @PostMapping("/respuestas/update/{id}")
    public String updateRespuestas(@PathVariable("id") long id,@Valid RespuestaPqrs respuestaPqrs,BindingResult result,Model model){
        if (result.hasErrors()){
            List<Pqrs> pqrsList = iPqrsRepository.findAll();
            model.addAttribute("pqrsList", pqrsList);
            return "AtencionCliente/RespuestasPqrs/edit";
        }
        respuestaPqrs.setIdrespuesta(id);
        iRespuestaPqrsRepository.save(respuestaPqrs);
        return "redirect:/respuestas/all";
    }

    @GetMapping("/respuestas/delete/{id}")
    public String deleteRespuesta(Model model, @PathVariable long id){
        try {
            iRespuestaPqrsRepository.deleteById(id);
            return "redirect:/respuestas/all";
        }catch (Exception exception){
            return "redirect:/respuestas/all";
        }
    }
}
