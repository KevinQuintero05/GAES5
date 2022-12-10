package com.example.demo.controller;

import com.example.demo.Enums.TipoReporteEnum;
import com.example.demo.Service.FacturaReservaService;
import com.example.demo.entity.Cronograma;
import com.example.demo.entity.Conductor;
import com.example.demo.entity.FacturaReservaDTO;
import com.example.demo.repository.ICronogramaRepository;
import com.example.demo.repository.IConductorRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class CronogramaController {

    @Autowired
    private ICronogramaRepository iCronogramaRepository;

    @Autowired
    private IConductorRepository iConductorRepository;

    @Autowired
    private FacturaReservaService facturaReservaService;


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

    /* ------------ Reporte --------------------*/
    @GetMapping("/cronograma/tarifa")
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

}

