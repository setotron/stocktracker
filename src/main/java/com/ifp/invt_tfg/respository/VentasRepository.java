package com.ifp.invt_tfg.respository;

import com.ifp.invt_tfg.model.Productos;
import com.ifp.invt_tfg.model.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {

    @Query("SELECT v FROM Ventas v WHERE" +
            " CONCAT(v.id, v.cliente.empresa, v.fecha)" +
            " LIKE %?1%")
    List<Ventas> findAll(String filtro);

}
