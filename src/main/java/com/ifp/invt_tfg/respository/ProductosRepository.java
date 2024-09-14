package com.ifp.invt_tfg.respository;

import com.ifp.invt_tfg.model.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos,Integer> {

    @Query("SELECT p FROM Productos p WHERE" +
            " CONCAT(p.id, p.nombre, p.marca, p.usuarios.nombre)" +
            " LIKE %?1%")
    Page<Productos> findAll(Pageable page, String filtro);

    @Query("SELECT p FROM Productos p WHERE" +
            " CONCAT(p.nombre, p.codigo)" +
            " LIKE %?1%")
    List<Productos> findAll(String filtro);

    Productos findFirstByCodigo(String codigo);

}
