package com.ifp.invt_tfg.controller;

import com.ifp.invt_tfg.model.Roles;
import com.ifp.invt_tfg.model.Usuarios;
import com.ifp.invt_tfg.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService serviceUsuarios;


    @Autowired
    private PasswordEncoder passwordEncode;


    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuarios> lista = serviceUsuarios.buscarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";
    }

    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.activar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue activado y tiene acceso al sistema.");
        return "redirect:/usuarios/index";
    }
    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.bloquear(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/usuarios/index";
    }

    @RequestMapping(value="/save")
    public String save(Usuarios usuarios,RedirectAttributes attributes){

        String pwdPlano=usuarios.getPassword();
        String pwdEncriptado=passwordEncode.encode(pwdPlano);
        usuarios.setPassword(pwdEncriptado);
        //usuarios.setRoles(null);
        serviceUsuarios.guardar(usuarios);
        attributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        // Eliminamos el usuario
        serviceUsuarios.eliminar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idUsuario, Model model){
        Usuarios usuarios = serviceUsuarios.buscarPorId(idUsuario);


        List<Roles> listRoles = serviceUsuarios.listRoles();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("listRoles",listRoles);
        usuarios.setRoles(null);


        return "usuarios/formRegistro";

    }



}
