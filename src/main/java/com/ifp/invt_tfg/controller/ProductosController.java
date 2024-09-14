package com.ifp.invt_tfg.controller;


import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.service.IProductosService;
import com.ifp.invt_tfg.service.IUsuariosService;
import com.ifp.invt_tfg.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping(value="/productos")
public class ProductosController {


    @Value("C:/TFG-INVT/prod/")
    private String ruta;

    @Autowired
    @Qualifier("productosServiceJpa")
    IProductosService serviceProductos;

    @Autowired
    IUsuariosService usuariosRepo;

    @RequestMapping(value = "/index", method= RequestMethod.GET)
    public String MostrarIndex(Model model, Pageable page, @Param("filtro") String filtro,RedirectAttributes attributes){
        Page<Productos> lista = serviceProductos.buscarTodos(page, filtro);
        model.addAttribute("productos",lista);
        model.addAttribute("filtro", filtro);
        for(Productos productos : lista){
            int stock =productos.getUnidades();

            if(stock<50){
                attributes.addFlashAttribute("msg", "Stock bajo para "+productos.getNombre());
            }else if(stock>100){
                attributes.addFlashAttribute("msg", "Stock Alto para "+productos.getNombre());
            }
        }
        return "productos/listProductos";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page, @Param("filtro") String filtro){
        Page<Productos> lista = serviceProductos.buscarTodos(page, filtro);
        model.addAttribute("productos",lista);
        model.addAttribute("filtro", filtro);
        return "productos/listProductos";
    }

    @RequestMapping(value = "/save", method= RequestMethod.POST)
    public String guardar(Productos productos, BindingResult result, @AuthenticationPrincipal UserDetails currentUser, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart){
        if(result.hasErrors()){
            for(ObjectError error : result.getAllErrors()){
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "productos/formProductos";
        }
        if(!multiPart.isEmpty()){

            String nombreImagen= Utileria.guardarArchivo(multiPart,ruta);
            if(nombreImagen !=null){
                productos.setImagen(nombreImagen);
            }

        }

        //productos.setFechaEntrada(LocalDate.now());
        productos.setUsuarios(usuariosRepo.buscarPorUsername(currentUser.getUsername()));
        serviceProductos.guardar(productos);
        attributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/productos/index";
    }


    @RequestMapping(value = "/create", method= RequestMethod.GET)
    public String crear(Productos productos,Model model){

        model.addAttribute("productos",productos);
        return "productos/formProductos";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idProductos, Model model){

        Productos productos = serviceProductos.buscarPorId(idProductos);
        model.addAttribute("productos",productos);
        return "productos/formProductos";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idProductos,Model model){
        serviceProductos.eliminar(idProductos);
        model.addAttribute("msg","El productos fue eliminado");
        return "redirect:/productos/index";
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,false));
    }


}
