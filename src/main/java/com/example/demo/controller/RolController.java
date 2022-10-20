package com.example.demo.controller;

import com.example.demo.entity.Rol;
import com.example.demo.repository.IRolRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RolController {

    private final IRolRepository iRolRepository;

    public RolController( IRolRepository iRolRepository){
        this.iRolRepository = iRolRepository;
    }

    @GetMapping("/roles")
    public List<Rol> GetRols(){
        return iRolRepository.findAll();
    }

    @PostMapping("/roles/new")
    public Rol Newrol(@RequestBody Rol newRol) {
        return iRolRepository.save(newRol);
    }

    @GetMapping("/roles/{id}")
    public Rol getRol(@PathVariable Long id ){
        return iRolRepository.findById(id).get();
    }

    @PutMapping("/roles/{id}")
    public Rol UpdateRol(@RequestBody Rol rol, @PathVariable long id){
        Rol roldb = iRolRepository.findById(id).get();
        rol.setDescripcion(rol.getDescripcion());
        return iRolRepository.save(roldb);
    }

    @DeleteMapping("/roles/{id}")
    public void DeleteRoles(@PathVariable long id)
    {
        iRolRepository.deleteById(id);
    }

}
