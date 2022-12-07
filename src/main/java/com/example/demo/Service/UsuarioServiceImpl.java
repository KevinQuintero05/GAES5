package com.example.demo.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public void saveUser(Usuario user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        user.setRole(Role.USER);
        usuarioRepository.save(user);
    }

    @Override
    public List<Object> isUserPresent(Usuario usuario) {
        boolean userExists = false;
        String message = null;
        Optional<Usuario> existingUserEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if(existingUserEmail.isPresent()){
            userExists = true;
            message = "Este correo ya esta resgitrado!.";
        }

        Optional<Usuario> existingUserMobile = usuarioRepository.findByTelefono(usuario.getTelefono());
        if(existingUserMobile.isPresent()){
            userExists = true;
            message = "Este numero de telefono ya esta resgitrado!.";
        }

        if (existingUserEmail.isPresent() && existingUserMobile.isPresent()) {
            message = "Tanto el correo como el numero de telefono ya existen!.";
        }

        System.out.println("existingUserEmail.isPresent() - "+existingUserEmail.isPresent()+"existingUserMobile.isPresent() - "+existingUserMobile.isPresent());
        return Arrays.asList(userExists, message);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", email)
                ));
    }
}
