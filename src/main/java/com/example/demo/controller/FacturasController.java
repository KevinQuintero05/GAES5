package com.example.demo.controller;

import com.example.demo.entity.Cronograma;
import com.example.demo.entity.Facturas;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.ICronogramaRepository;
import com.example.demo.repository.IFacturasRepository;
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

import java.util.List;

@Controller
public class FacturasController {

    @Autowired
    private IFacturasRepository iFacturasRepository;

    @Autowired
    private ICronogramaRepository iCronogramaRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @GetMapping("/facturas/all")
    public String GetFacturas(Model model){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();

            List<Facturas> facturasList = iFacturasRepository.getFacturasByIdusuario(loginUser.getIdusuario());

            //List<Facturas> facturasList = iFacturasRepository.findAll();
            model.addAttribute("facturasList", facturasList);
            return "AtencionCliente/Facturas/Facturas";
        }catch (Exception ex){
            return "error";
        }
    }

    //ADMIN
    @GetMapping("/facturas-admin/all")
    public String GetFacturasAdmin(Model model){

        try{
            List<Facturas> facturasList = iFacturasRepository.findAll();
            model.addAttribute("facturasList", facturasList);
            return "Reservas/Facturas/Facturas-Admin";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping("/facturas/new")
    public  String GetShowCreateFacturas(Model model){
        List<Cronograma> cronograma = iCronogramaRepository.findAll();
        model.addAttribute("cronograma", cronograma);
        model.addAttribute("facturas", new Facturas());
        return "Reservas/Facturas/Create";
    }

    @PostMapping("/facturas/save")
    public String SaveFacturas(Facturas facturas){
        iFacturasRepository.save(facturas);
        return "redirect:/facturas/all";
    }

    @GetMapping("/facturas/edit/{id}")
    public String showUpdateFacturas(Model model, @PathVariable long id){
        Facturas facturasbd = iFacturasRepository.findById(id).get();
        model.addAttribute("cronograma", iCronogramaRepository.findAll());
        model.addAttribute("facturas",facturasbd);
        return "Reservas/facturas/edit";
    }

    @PostMapping("/facturas/update/{id}")
    public String updateFacturas(@PathVariable("id") long id, Facturas facturas, Model model){
        facturas.setIdFactura(id);
        iFacturasRepository.save(facturas);
        return "redirect:/facturas/all";
    }

    @GetMapping("/facturas/delete/{id}")
    public String deleteFacturas(Model model, @PathVariable long id){
        iFacturasRepository.deleteById(id);
        return "redirect:/facturas/all";
    }
}
