package com.ifp.invt_tfg.service.db;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Pedidos;
import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.respository.ClientesRepository;
import com.ifp.invt_tfg.respository.PedidosRepository;
import com.ifp.invt_tfg.respository.ProductosRepository;
import com.ifp.invt_tfg.service.IPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServiceJPA implements IPedidosService {

    @Autowired
    private PedidosRepository pediRepo;

    @Autowired
    private ClientesRepository clientRepo;

    @Autowired
    private ProductosRepository productosRepo;

    @Override
    public void guardar(Pedidos Ped) {
        pediRepo.save(Ped);

    }

    @Override
    public List<Cliente> buscarTodosCli(String filtro) {
        if(filtro != null){
            return clientRepo.findFilter(filtro);
        }
        return clientRepo.findAll();
    }

    @Override
    public List<Productos> buscarTodosProd(String filtro) {
        if(filtro != null){
            return productosRepo.findAll(filtro);
        }
        return productosRepo.findAll();
    }
}
