package com.ifp.invt_tfg.respository;

import com.ifp.invt_tfg.model.Roles;
import com.ifp.invt_tfg.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
