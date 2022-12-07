package com.example.demo.controller;

import com.example.demo.entity.Solicitudes;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Vehiculos;
import com.example.demo.repository.IVehiculosRepository;
import com.example.demo.repository.ISolicitudesRepository;
import com.example.demo.repository.IservicioRepository;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SolicitudesController {

    @Autowired
    private ISolicitudesRepository iSolicitudesRepository;

    @Autowired
    private IVehiculosRepository iVehiculosRepository;

    @Autowired
    private IservicioRepository iservicioRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;


    @GetMapping("/solicitudes/all")
    public String GetSolicitudes(Model model) {

        try {
            List<Solicitudes> solicitudesList = iSolicitudesRepository.findAll();
            model.addAttribute("solicitudesList", solicitudesList);
            return "Reservas/Solicitudes/Solicitudes";
        } catch (Exception ex) {
            return "error";
        }
    }

    @GetMapping("/solicitudes/new")
    public String GetShowCreateSolicitudes(Model model) {
        List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
        List<Servicio> servicios = iservicioRepository.findAll();
        List<Usuario> usuarios = iUsuarioRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("servicios", servicios);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("solicitud", new Solicitudes());
        return "Reservas/Solicitudes/Create";
    }
    @PostMapping("/solicitudes/save")
    public String SaveSolicitudes(Solicitudes solicitudes){
        iSolicitudesRepository.save(solicitudes);
        return "redirect:/Solicitudes/all";
    }
    @GetMapping("/solicitudes/edit/{id}")
    public String showUpdatesSolicitudes(Model model, @PathVariable long id){
        Solicitudes solicitudesbd = isolicitudesRepository.findById(id).get();
        model.addAttribute("vehiculos", iVehiculosRepository.findAll());
        model.addAttribute("servicios", iserviciosRepository.findAll());
        model.addAttribute("usuarios", iVehiculosRepository.findAll());
        model.addAttribute("solicitudes",solicitudesbd);
        return "Reservas/Solicitudes/edit";
    }
    @PostMapping("/solicitudes/update/{id}")
    public String updateSolicitudes(@PathVariable("id") long id, solicitudes solicitudes, Model model){
        solicitudes.setIdSolicitudes(id);
        iSolicitudesRepository.save(solicitudes);
        return "redirect:/tarifas/all";
    }

    @GetMapping("/solicitudes/delete/{id}")
    public String deletesSolicitudes(Model model, @PathVariable long id){
        iSolicitudesRepository.deleteById(id);
        return "redirect:/solicitudes/all";
    }
}
}