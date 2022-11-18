package com.example.demo.controller;


import com.example.demo.entity.Pqrs;
import com.example.demo.entity.Vehiculos;
import com.example.demo.entity.Servicio;
import com.example.demo.repository.IVehiculosRepository;
import com.example.demo.repository.IservicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehiculoController {

    @Autowired
    private IVehiculosRepository iVehiculosRepository;

    @Autowired
    private IservicioRepository iservicioRepository;


    @GetMapping("/vehiculos")
    public String GetVehiculos(Model model) {

        try {
            List<Vehiculos> vehiculosList = iVehiculosRepository.findAll();
            model.addAttribute("vehiculosList", vehiculosList);
            return "Vehiculos/Vehiculos";
        } catch (Exception ex) {
            return "error";
        }
    }


    @GetMapping("/vehiculos/new")
    public  String GetShowCreateVehiculos(Model model){
        List<Servicio> servicios = iservicioRepository.findAll();
        model.addAttribute("servicios", servicios);
        model.addAttribute("vehiculos", new Vehiculos());
        return "Vehiculos/Create";
    }


    @PostMapping("/vehiculos/save")
    public String SaveVehiculos(Vehiculos vehiculos){
        iVehiculosRepository.save(vehiculos);
        return "redirect:/vehiculos";
    }


    @GetMapping("/vehiculos/edit/{id}")
    public String shouUpdateVehiculos(Model model, @PathVariable long id){
        Vehiculos vehiculos = iVehiculosRepository.findById(id).get();
        model.addAttribute("servicios", iservicioRepository.findAll());
        model.addAttribute("vehiculos",vehiculos);
        return "vehiculos/edit";
    }

    @PostMapping("/vehiculos/update/{id}")
    public String updateVehiculos(@PathVariable("id") long id, Vehiculos vehiculos, Model model){
        vehiculos.setIdvehiculo(id);
        iVehiculosRepository.save(vehiculos);
        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos/delete/{id}")
    public String deleteVehiculos(Model model, @PathVariable long id){
        iVehiculosRepository.deleteById(id);
        return "redirect:/vehiculos";
    }

}
