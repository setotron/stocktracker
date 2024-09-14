package com.ifp.invt_tfg.service.db;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Usuarios;
import com.ifp.invt_tfg.respository.ClientesRepository;
import com.ifp.invt_tfg.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceJpa implements IClientesService {

    @Autowired
    ClientesRepository clientRepo;

    @Override
    public void guardar(Cliente cliente) {
    clientRepo.save(cliente);
    }

    @Override
    public void eliminar(Integer idCliente) {
    clientRepo.deleteById(idCliente);
    }

    @Override
    public Cliente buscarPorId(Integer idCliente) {
        Optional <Cliente> optional=clientRepo.findById(idCliente);
        if(optional.isPresent()) return optional.get();

        return null;
    }

    @Override
    public Cliente BuscarPorNumRefr(String NumRefr) {
        return clientRepo.findByNumRefr(NumRefr);
    }

    @Override
    public List<Cliente> buscarTodos() {

        return clientRepo.findAll();
    }

    @Override
    public List<Cliente> buscarTodos(String filtro) {
        if(filtro != null){
            return clientRepo.findAll(filtro);
        }
        return clientRepo.findAll();
    }
}
