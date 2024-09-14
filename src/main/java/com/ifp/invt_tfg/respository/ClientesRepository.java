package com.ifp.invt_tfg.respository;

import com.ifp.invt_tfg.model.Cliente;
import com.ifp.invt_tfg.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente,Integer> {

    @Query("SELECT c FROM Cliente c WHERE" +
            " CONCAT(c.id, c.nombre, c.empresa)" +
            " LIKE %?1%")
    List<Cliente> findAll(String filtro);

    @Query("SELECT c FROM Cliente c WHERE" +
            " CONCAT(c.nombre,c.empresa, c.numRefr)" +
            " LIKE %?1%")
    List<Cliente> findFilter(String filtro);

    Cliente findByNumRefr(String NumRfer);
}
