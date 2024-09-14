package com.ifp.invt_tfg.service.db;


import com.ifp.invt_tfg.model.Roles;
import com.ifp.invt_tfg.model.Usuarios;
import com.ifp.invt_tfg.respository.RolesRepository;
import com.ifp.invt_tfg.respository.UsuariosRepository;
import com.ifp.invt_tfg.service.IUsuariosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceJpa implements IUsuariosService {


    @Autowired
    private UsuariosRepository usuariosRepo;

    @Autowired
    RolesRepository roleRepo;



    @Override
    public void guardar(Usuarios usuario) {
        usuariosRepo.save((usuario));
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepo.deleteById(idUsuario);
    }

    @Override
    public List<Usuarios> buscarTodos() {
        return usuariosRepo.findAll();
    }

    @Override
    public List<Usuarios> buscarRegistrados() {
        return usuariosRepo.findByFechaRegistroNotNull();
    }

    @Override
    public List<Roles> listRoles() {
        return roleRepo.findAll();
    }


    @Override
    public Usuarios buscarPorId(Integer idUsuario) {
        Optional<Usuarios> optional=usuariosRepo.findById(idUsuario);

        if(optional.isPresent()) return optional.get();

        return null;
    }



    @Override
    public Usuarios buscarPorUsername(String username) {
        return usuariosRepo.findByUsername(username);
    }

    @Transactional
    @Override
    public int bloquear(int idUsuario) {
        return usuariosRepo.lock(idUsuario);
    }

    @Transactional
    @Override
    public int activar(int idUsuario) {
        return usuariosRepo.unlock(idUsuario);
    }


}
