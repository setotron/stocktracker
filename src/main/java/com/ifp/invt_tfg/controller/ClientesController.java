package com.ifp.invt_tfg.controller;


import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/clientes")
public class ClientesController {

    @Autowired
    IClientesService serviceClient;


    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String MostrarIndex(Model model, @Param("filtro") String filtro){
        List<Cliente> lista = serviceClient.buscarTodos(filtro);
        model.addAttribute("clientes",lista);
        model.addAttribute("filtro", filtro);
        return "clientes/listClientes";
    }


    @RequestMapping(value= "save", method = RequestMethod.POST)
    public String Guardar(Cliente cliente, BindingResult result, RedirectAttributes attributes){
        serviceClient.guardar(cliente);
        attributes.addFlashAttribute("msg","Registro Guardado");
        return "redirect:/clientes/index";
    }

    @RequestMapping(value = "/create", method= RequestMethod.GET)
    public String crear(Cliente cliente, Model model){

        model.addAttribute("clientes",cliente);
        return "clientes/formClientes";
    }


    @GetMapping("/edit/{id}")
    public String Editar(@PathVariable("id") int idCliente,Model model){
        Cliente cliente = serviceClient.buscarPorId(idCliente);
        model.addAttribute("clientes",cliente);
        return "clientes/formClientes";

    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idProductos,Model model){
        serviceClient.eliminar(idProductos);
        model.addAttribute("msg","El cliente fue eliminado");
        return "redirect:/clientes/index";



    }



}
