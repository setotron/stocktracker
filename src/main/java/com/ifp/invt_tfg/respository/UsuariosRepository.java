package com.ifp.invt_tfg.respository;

import com.ifp.invt_tfg.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByUsername(String username);
    List<Usuarios> findByFechaRegistroNotNull();


    @Modifying
    @Query("UPDATE Usuarios u SET u.status=0 WHERE u.id= :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);

    @Modifying
    @Query("UPDATE Usuarios u SET u.status=1 WHERE u.id= :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);



}
