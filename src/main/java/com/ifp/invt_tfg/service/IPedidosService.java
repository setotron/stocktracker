package com.ifp.invt_tfg.service;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Pedidos;
import com.ifp.invt_tfg.model.Productos;

import java.util.List;

public interface IPedidosService {

    void guardar(Pedidos Ped);

    List<Cliente> buscarTodosCli(String filtro);

    List<Productos> buscarTodosProd(String filtro);
}
