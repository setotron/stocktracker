package com.ifp.invt_tfg.controller;


import com.ifp.invt_tfg.model.Ventas;
import com.ifp.invt_tfg.service.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/ventas")
public class VentasController {

    @Autowired
    IVentasService serviceVent;

    @GetMapping(value = "/index")
    public String mostrarVentas(Model model, @Param("filtro") String filtro){
        List<Ventas> ventas = serviceVent.buscarTodos(filtro);
        model.addAttribute("ventas", ventas);
        model.addAttribute("filtro", filtro);
        return "ventas/listVentas";

    }
}
