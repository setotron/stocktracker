package com.ifp.invt_tfg.service;

import com.ifp.invt_tfg.model.Ventas;

import java.util.List;

public interface IVentasService {

    List<Ventas> buscarTodos();

    List<Ventas> buscarTodos(String filtro);

    Ventas guardar(Ventas ventas);
}
