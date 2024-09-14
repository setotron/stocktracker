package com.ifp.invt_tfg.controller;

import com.ifp.invt_tfg.model.Roles;
import com.ifp.invt_tfg.model.Usuarios;
import com.ifp.invt_tfg.service.IProductosService;
import com.ifp.invt_tfg.service.IUsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    @Qualifier("productosServiceJpa")
    IProductosService serviceProductos;

    @Autowired
    private PasswordEncoder passwordEncode;


    @GetMapping("/about")
    public String mostrarAcerca() {
        return "acerca";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request ) {
        SecurityContextLogoutHandler logoutHandler=new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }

    @GetMapping("/signup")
    public String registrarse(Usuarios usuarios) {
        return "formRegistro";
    }


    @PostMapping("/signup")
    public String guardarRegistro(Usuarios usuario, RedirectAttributes attributes) {

        String pwdPlano=usuario.getPassword();
        String pwdEncriptado=passwordEncode.encode(pwdPlano);

        usuario.setPassword(pwdEncriptado);
        usuario.setStatus(1); // Activado por defecto

        usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor



        // Creamos el Perfil que le asignaremos al usuario nuevo
        Roles Rol = new Roles();
        Rol.setId(3) ; // Perfil EMPLEADO
        usuario.agregar(Rol);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
         */
        serviceUsuarios.guardar(usuario);
        attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

        return "redirect:/login";
    }



    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto) {
        return texto+" Encriptado en Bcrypt "+passwordEncode.encode(texto);

    }

    @GetMapping("/")
    public String mostrarHome(Model model){
        return "home";
    }
}
