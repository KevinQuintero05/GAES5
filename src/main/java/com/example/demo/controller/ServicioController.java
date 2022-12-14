package com.example.demo.controller;

import com.example.demo.entity.Servicio;
import com.example.demo.repository.IservicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ServicioController {

    @Autowired
    private IservicioRepository iservicioRepository;

    @GetMapping("/servicios")
    public String Getservicios(Model model){
        try {
            List<Servicio> servicios = iservicioRepository.findAll();
            model.addAttribute("servicios", servicios);
            return "Services/Servicios/Servicios";
        } catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/servicios/new")
    public String GetShowCreateServicio(Model model){
        model.addAttribute("servicio", new Servicio());
        return "Services/Servicios/Create";
    }

    @PostMapping("/servicios/save")
    public String SaveServicio(@Valid Servicio servicio, BindingResult result) {

        if (result.hasErrors()) {
            return "Services/Servicios/Create";
        }
        iservicioRepository.save(servicio);
        return "redirect:/servicios";
    }
    @GetMapping("/servicios/edit/{id}")
    public String showUpdateServicios(Model model, @PathVariable long id){
        Servicio serviciobd = iservicioRepository.findById(id).get();
        model.addAttribute("servicio", serviciobd);
        return "Services/Servicios/edit";
    }

    @PostMapping("/servicios/update/{id}")
    public String updateServicios(@PathVariable("id") long id,@Valid Servicio servicio, BindingResult result, Model model){

        if (result.hasErrors()) {
            Servicio serviciobd = iservicioRepository.findById(id).get();
            model.addAttribute("servicio", serviciobd);
            return "Services/Servicios/Edit";

        }

        servicio.setIdservicio(id);
        iservicioRepository.save(servicio);
        return "redirect:/servicios";

    }

    @GetMapping("/servicios/delete/{id}")
    public String deleteServicios(Model model,@PathVariable long id){
        try {
            iservicioRepository.deleteById(id);
            return "redirect:/servicios";
        }catch (Exception exception){
            return "redirect:/servicios";
        }
    }
}
