package com.ifp.invt_tfg.service.db;


import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.respository.ProductosRepository;
import com.ifp.invt_tfg.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceJpa implements IProductosService {

    @Autowired
    private ProductosRepository productosRepo;


    @Override
    public List<Productos> buscarTodos() {
        return productosRepo.findAll();
    }


    @Override
    public Productos buscarPorId(Integer idProd) {
        Optional<Productos> optional = productosRepo.findById((idProd));
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Productos buscarPorCodigo(String codigo) {
        return productosRepo.findFirstByCodigo(codigo);
    }

    @Override
    public void guardar(Productos prod) {
        productosRepo.save(prod);


    }

    @Override
    public void eliminar(Integer idProd) {
        productosRepo.deleteById(idProd);
    }

    @Override
    public Page<Productos> buscarTodos(Pageable page, String filtro) {
        if(filtro != null){
            return productosRepo.findAll(page, filtro);
        }
        return productosRepo.findAll(page);
    }
}
