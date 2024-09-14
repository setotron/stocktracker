package com.ifp.invt_tfg.service;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductosService {

    List<Productos> buscarTodos();

    Productos buscarPorId(Integer idProd);

    Productos buscarPorCodigo(String codigo);

    void guardar(Productos prod);

    void eliminar(Integer idProd);

    //Paginación
    Page<Productos> buscarTodos(Pageable page, String filtro);



}
