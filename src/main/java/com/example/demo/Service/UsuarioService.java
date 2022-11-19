package com.example.demo.Service;

import com.example.demo.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public void saveUser(Usuario usuario);

    public List<Object> isUserPresent(Usuario usuario);
}
