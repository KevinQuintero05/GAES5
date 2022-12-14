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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();
            List<Solicitudes> solicitudesList = iSolicitudesRepository.getSolicitudesByIdusuario(loginUser.getIdusuario());
            model.addAttribute("solicitudesList", solicitudesList);
            return "Reservas/Solicitudes/Solicitudes";
        } catch (Exception ex) {
            return "error";
        }
    }

    @GetMapping("/solicitudes-admin/all")
    public String GetSolicitudesAdmin(Model model) {

        try {
            List<Solicitudes> solicitudesList = iSolicitudesRepository.findAll();
            model.addAttribute("solicitudesList", solicitudesList);
            return "Reservas/Solicitudes/Solicitudes-admin";
        } catch (Exception ex) {
            return "error";
        }
    }



    @GetMapping("/solicitudes/new")
    public String GetShowCreateSolicitudes(Model model) {
        List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
        List<Servicio> servicio = iservicioRepository.findAll();
        List<Usuario> usuario = iUsuarioRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("usuario", usuario);
        model.addAttribute("solicitudes", new Solicitudes());
        return "Reservas/Solicitudes/Create";
    }
    @PostMapping("/solicitudes/save")
    public String SaveSolicitudes(@Valid Solicitudes solicitudes, BindingResult result, Model model){

        if(result.hasErrors()){
            List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
            List<Servicio> servicio = iservicioRepository.findAll();
            List<Usuario> usuario = iUsuarioRepository.findAll();
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("servicio", servicio);
            model.addAttribute("usuario", usuario);
            return "Reservas/Solicitudes/Create";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        solicitudes.setUsuario(loginUser);

        iSolicitudesRepository.save(solicitudes);
        return "redirect:/solicitudes/all";
    }

    @GetMapping("/solicitudes/edit/{id}")
    public String showUpdatesSolicitudes(Model model, @PathVariable long id){
        Solicitudes solicitudesbd = iSolicitudesRepository.findById(id).get();
        model.addAttribute("vehiculos", iVehiculosRepository.findAll());
        model.addAttribute("servicio", iservicioRepository.findAll());
        model.addAttribute("usuario", iUsuarioRepository.findAll());
        model.addAttribute("solicitudes",solicitudesbd);
        return "Reservas/Solicitudes/edit";
    }


    @PostMapping("/solicitudes/update/{id}")
    public String updateSolicitudes(@PathVariable("id") long id,@Valid Solicitudes solicitudes, BindingResult result,Model model){
        if(result.hasErrors()){
            List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
            List<Servicio> servicio = iservicioRepository.findAll();
            List<Usuario> usuario = iUsuarioRepository.findAll();
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("servicio", servicio);
            model.addAttribute("usuario", usuario);
            return "Reservas/Solicitudes/Edit";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();

        solicitudes.setUsuario(loginUser);

        solicitudes.setIdSolicitudes(id);
        iSolicitudesRepository.save(solicitudes);
        return "redirect:/solicitudes/all";
    }

    @GetMapping("/solicitudes/delete/{id}")
    public String deletesSolicitudes(Model model, @PathVariable long id){
        try{
            iSolicitudesRepository.deleteById(id);
            return "redirect:/solicitudes/all";
        }catch (Exception exception){
            return "redirect:/solicitudes/all";
        }
    }
}