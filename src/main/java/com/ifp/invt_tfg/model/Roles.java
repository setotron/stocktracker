package com.ifp.invt_tfg.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Roles")
    private String roles;

    @ManyToMany(mappedBy = "roles")
    Set<Usuarios> RolUsu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString(){
        return this.roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roles roles1 = (Roles) o;

        if (id != roles1.id) return false;
        if (roles != null ? !roles.equals(roles1.roles) : roles1.roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
