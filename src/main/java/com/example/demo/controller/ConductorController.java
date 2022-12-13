package com.example.demo.controller;


import com.example.demo.entity.Conductor;
import com.example.demo.repository.IConductorRepository;
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
public class ConductorController {

    @Autowired
    private IConductorRepository iConductorRepository;


    @GetMapping("/conductor")
    public String GetConductor(Model model){

        try{
            List<Conductor> conductor = iConductorRepository.findAll();
            model.addAttribute("conductor", conductor);
            return "Reservas/Conductor/Conductor";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/conductor/new")
    public  String GetShowCreateConductor(Model model){
        model.addAttribute("conductor", new Conductor());
        return "Reservas/Conductor/Create";
    }

    @PostMapping("/conductor/save")
    public String SaveConductor(@Valid Conductor conductor, BindingResult result, Model model){
        if(result.hasErrors()){
            return "Reservas/Conductor/Create";
        }
        iConductorRepository.save(conductor);
        return "redirect:/conductor";
    }

    @GetMapping("/conductor/edit/{id}")
    public String showUpdateConductor(Model model, @PathVariable long id){
        Conductor conductorbd = iConductorRepository.findById(id).get();
        model.addAttribute("conductor",conductorbd);
        return "Reservas/conductor/edit";
    }

    @PostMapping("/conductor/update/{id}")
    public String updateConductor(@PathVariable("id") long id,@Valid Conductor conductor, BindingResult result,Model model){
        if(result.hasErrors()){
            return "Reservas/conductor/edit";
        }
        conductor.setIdConductor(id);
        iConductorRepository.save(conductor);
        return "redirect:/conductor";
    }

    @GetMapping("/conductor/delete/{id}")
    public String deleteConductor(Model model, @PathVariable long id){
        iConductorRepository.deleteById(id);
        return "redirect:/conductor";
    }
}


