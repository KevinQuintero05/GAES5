package com.example.demo.controller;

import com.example.demo.Enums.TipoReporteEnum;
import com.example.demo.Service.FacturaReservaService;
import com.example.demo.Service.ReporteCronogramaService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class CronogramaController {

    @Autowired
    private ICronogramaRepository iCronogramaRepository;

    @Autowired
    private IservicioRepository iservicioRepository;

    @Autowired
    private ISolicitudesRepository iSolicitudesRepository;

    @Autowired
    private IVehiculosRepository iVehiculosRepository;
    @Autowired
    private IConductorRepository iConductorRepository;

    @Autowired
    private FacturaReservaService facturaReservaService;

    @Autowired
    private ReporteCronogramaService reporteCronogramaService;


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

    @GetMapping("/cronograma-cliente/all")
    public String GetCronogramaCliente(Model model){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario loginUser = (Usuario)authentication.getPrincipal();
            List<Cronograma> cronogramaList = iCronogramaRepository.getCronogramaByidusuario(loginUser.getIdusuario());
            model.addAttribute("cronogramaList", cronogramaList);
            return "Reservas/Cronograma/Cronograma-cliente";
        }catch (Exception ex){
            return "error";
        }
    }


    @GetMapping("/cronograma/new")
    public  String GetShowCreateCronograma(Model model){
        List<Solicitudes> solicitudes = iSolicitudesRepository.findAll();
        List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
        List<Servicio> servicio = iservicioRepository.findAll();
        List<Conductor> conductor = iConductorRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("solicitudes",solicitudes);
        model.addAttribute("conductor",conductor);

        model.addAttribute("cronograma", new Cronograma());
        return "Reservas/Cronograma/CrearReserva";
    }

    @PostMapping("/cronograma/save")
    public String SaveCronograma(@Valid Cronograma cronograma, BindingResult result, Model model){

        if(result.hasErrors()){
            List<Solicitudes> solicitudes = iSolicitudesRepository.findAll();
            List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
            List<Servicio> servicio = iservicioRepository.findAll();
            List<Conductor> conductor = iConductorRepository.findAll();
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("servicio", servicio);
            model.addAttribute("solicitudes",solicitudes);
            model.addAttribute("conductor",conductor);
            return "Reservas/Cronograma/CrearReserva";
        }
        iCronogramaRepository.save(cronograma);
        return "redirect:/cronograma/all";
    }

    @GetMapping("/cronograma/edit/{id}")
    public String showUpdateCronograma(Model model, @PathVariable long id){
        Cronograma cronogramabd = iCronogramaRepository.findById(id).get();
        List<Solicitudes> solicitudes = iSolicitudesRepository.findAll();
        List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
        List<Servicio> servicio = iservicioRepository.findAll();
        List<Conductor> conductor = iConductorRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("solicitudes",solicitudes);
        model.addAttribute("conductor",conductor);;
        model.addAttribute("cronograma",cronogramabd);
        return "Reservas/Cronograma/edit";
    }

    @PostMapping("/cronograma/update/{id}")
    public String updateCronograma(@PathVariable("id") long id,@Valid Cronograma cronograma, BindingResult result, Model model){
        if(result.hasErrors()){
            List<Solicitudes> solicitudes = iSolicitudesRepository.findAll();
            List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
            List<Servicio> servicio = iservicioRepository.findAll();
            List<Conductor> conductor = iConductorRepository.findAll();
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("servicio", servicio);
            model.addAttribute("solicitudes",solicitudes);
            model.addAttribute("conductor",conductor);
            return "Reservas/Cronograma/edit";
        }
        cronograma.setNoReserva(id);
        iCronogramaRepository.save(cronograma);
        return "redirect:/cronograma/all";
    }

    @GetMapping("/cronograma/delete/{id}")
    public String deleteCronograma(Model model, @PathVariable long id){
        try {
            iCronogramaRepository.deleteById(id);
            return "redirect:/cronograma/all";
        }catch (Exception exception){
            return "redirect:/cronograma/all";
        }
    }

    /*
    @GetMapping("/cronograma-cliente/factura")
    public String showFormReport(){

        return "Reportes/Factura";
    }*/

    @GetMapping("/cronograma-cliente/factura")
    public String showFormReport(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loginUser = (Usuario)authentication.getPrincipal();
        List<Cronograma> cronogramaList = iCronogramaRepository.getCronogramaByidusuario(loginUser.getIdusuario());
        model.addAttribute("cronogramaList", cronogramaList);
        return "Reportes/Factura";
    }



    @GetMapping("/cronograma/adminreporte")
    public String showFormReport1(Model model){
        List<Vehiculos> vehiculos = iVehiculosRepository.findAll();
        List<Servicio> servicio = iservicioRepository.findAll();
        List<Conductor> conductor = iConductorRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("conductor",conductor);
        return "Reportes/Cronograma";
    }


    /* ------------ Reporte Tarifa--------------------*/
    @GetMapping("/cronograma-cliente/tarifa")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params) throws JRException, IOException, SQLException {
        FacturaReservaDTO dto = facturaReservaService.obtenerReporteProducto(params);
        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if(params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())){
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }else{
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLenght()).contentType(mediaType).body(streamResource);

    }


    /* ------------ Reporte Cronograma--------------------*/
    @GetMapping("/cronograma-admin/reporte")
    public ResponseEntity<Resource> report(@RequestParam Map<String, Object> params) throws  JRException, IOException, SQLException {
        ReporteCronogramaDTO dto = reporteCronogramaService.obtenerCronograma(params);
        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())){
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }else {
            mediaType = MediaType.APPLICATION_PDF;
        }
        return  ResponseEntity.ok().header("Content-Disposition","inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLenght()).contentType(mediaType).body(streamResource);
    }
}