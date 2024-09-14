package com.ifp.invt_tfg.service;

import com.ifp.invt_tfg.model.Roles;
import com.ifp.invt_tfg.model.Usuarios;

import java.util.List;

public interface IUsuariosService {

    void guardar(Usuarios usuario);

    void eliminar(Integer idUsuario);

    List<Usuarios> buscarTodos();
    List<Usuarios> buscarRegistrados();

    List<Roles> listRoles();

    Usuarios buscarPorId(Integer idUsuario);
    Usuarios buscarPorUsername(String username);

    int bloquear(int idUsuario);
    int activar(int idUsuario);

}
