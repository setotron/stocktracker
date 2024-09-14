package com.ifp.invt_tfg.service;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Usuarios;

import java.util.List;

public interface IClientesService {

    void guardar(Cliente cliente);

    void eliminar(Integer idCliente);

    Cliente buscarPorId(Integer idCliente);

    Cliente BuscarPorNumRefr(String NumRefr);

    List<Cliente> buscarTodos();

    List<Cliente> buscarTodos(String filtro);

}
