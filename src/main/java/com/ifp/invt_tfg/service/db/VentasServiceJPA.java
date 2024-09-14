package com.ifp.invt_tfg.service.db;

import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.model.Ventas;
import com.ifp.invt_tfg.respository.VentasRepository;
import com.ifp.invt_tfg.service.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceJPA implements IVentasService {

    @Autowired
    private VentasRepository ventasRepo;

    @Override
    public List<Ventas> buscarTodos() {
        return ventasRepo.findAll();
    }

    @Override
    public List<Ventas> buscarTodos(String filtro) {
        if(filtro != null){
            return ventasRepo.findAll(filtro);
        }
        return ventasRepo.findAll();
    }

    @Override
    public Ventas guardar(Ventas ventas) {
        ventasRepo.save(ventas);
        return ventas;
    }
}
