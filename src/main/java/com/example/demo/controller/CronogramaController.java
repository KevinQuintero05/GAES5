package com.example.demo.controller;

import com.example.demo.entity.Cronograma;
import com.example.demo.entity.Conductor;
import com.example.demo.repository.ICronogramaRepository;
import com.example.demo.repository.IConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CronogramaController {

    @Autowired
    private ICronogramaRepository iCronogramaRepository;

    @Autowired
    private IConductorRepository iConductorRepository;


    @GetMapping("/cronograma/all")
    public String GetCronograma(Model model){

        try{
            List<Cronograma> cronogramaList = iCronogramaRepository.findAll();
            model.addAttribute("cronogramaList", cronogramaList);
            return "Reservas/Cronograma/Cronograma";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/cronograma/new")
    public  String GetShowCreateCronograma(Model model){
        List<Conductor> conductor = iConductorRepository.findAll();
        model.addAttribute("conductor", conductor);
        model.addAttribute("cronograma", new Cronograma());
        return "Reservas/Cronograma/Create";
    }

    @PostMapping("/cronograma/save")
    public String SaveCronograma(Cronograma cronograma){
        iCronogramaRepository.save(cronograma);
        return "redirect:/cronograma/all";
    }

    @GetMapping("/cronograma/edit/{id}")
    public String showUpdateCronograma(Model model, @PathVariable long id){
        Cronograma cronogramabd = iCronogramaRepository.findById(id).get();
        model.addAttribute("conductor", iConductorRepository.findAll());
        model.addAttribute("cronograma",cronogramabd);
        return "Reservas/Cronograma/edit";
    }

    @PostMapping("/cronograma/update/{id}")
    public String updateCronograma(@PathVariable("id") long id, Cronograma cronograma, Model model){
        cronograma.setNoReserva(id);
        iCronogramaRepository.save(cronograma);
        return "redirect:/cronograma/all";
    }

    @GetMapping("/cronograma/delete/{id}")
    public String deleteCronograma(Model model, @PathVariable long id){
        iCronogramaRepository.deleteById(id);
        return "redirect:/cronograma/all";
    }
}
