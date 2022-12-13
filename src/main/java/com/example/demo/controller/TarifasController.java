package com.example.demo.controller;

import com.example.demo.entity.Tarifas;
import com.example.demo.entity.Cronograma;
import com.example.demo.repository.ICronogramaRepository;
import com.example.demo.repository.ITarifasRepository;
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
public class TarifasController {

    @Autowired
    private ITarifasRepository iTarifasRepository;

    @Autowired
    private ICronogramaRepository iCronogramaRepository;

    @GetMapping("/tarifas/all")
    public String GetTarifas(Model model){

        try{
            List<Tarifas> tarifasList = iTarifasRepository.findAll();
            model.addAttribute("tarifasList", tarifasList);
            return "Reservas/Tarifas/Tarifas";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/tarifas/new")
    public  String GetShowCreateTarifas(Model model){
        List<Cronograma> cronograma = iCronogramaRepository.findAll();
        model.addAttribute("cronograma", cronograma);
        model.addAttribute("tarifas", new Tarifas());
        return "Reservas/Tarifas/Create";
    }

    @PostMapping("/tarifas/save")
    public String SaveTarifas(@Valid Tarifas tarifas, BindingResult result, Model model){
        if(result.hasErrors()) {
            List<Cronograma> cronograma = iCronogramaRepository.findAll();
            model.addAttribute("cronograma", cronograma);

            return "Reservas/Tarifas/Create";
        }
        iTarifasRepository.save(tarifas);
        return "redirect:/tarifas/all";
    }

    @GetMapping("/tarifas/edit/{id}")
    public String showUpdateTarifas(Model model, @PathVariable long id){
        Tarifas tarifasbd = iTarifasRepository.findById(id).get();
        model.addAttribute("cronograma", iCronogramaRepository.findAll());
        model.addAttribute("tarifas",tarifasbd);
        return "Reservas/Tarifas/edit";
    }

    @PostMapping("/tarifas/update/{id}")
    public String updateTarifas(@PathVariable("id") long id, Tarifas tarifas, Model model){
        tarifas.setIdTarifa(id);
        iTarifasRepository.save(tarifas);
        return "redirect:/tarifas/all";
    }

    @GetMapping("/tarifas/delete/{id}")
    public String deleteTarifas(Model model, @PathVariable long id){
        iTarifasRepository.deleteById(id);
        return "redirect:/tarifas/all";
    }
}
