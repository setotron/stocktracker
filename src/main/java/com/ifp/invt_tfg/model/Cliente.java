package com.ifp.invt_tfg.model;

import jakarta.persistence.*;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "NumRefr")
    private String numRefr;
    @Basic
    @Column(name = "Correo")
    private String correo;
    @Basic
    @Column(name = "Empresa")
    private String empresa;
    @Basic
    @Column(name = "Direccion")
    private String direccion;
    @Basic
    @Column(name = "TotalPed")
    private int totalped;

    public int getTotalped() {
        return totalped;
    }

    public void plusTotalPed(){
        this.totalped++;
    }

    public void setTotalped(int totalped) {
        this.totalped = totalped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumRefr() {
        return numRefr;
    }

    public void setNumRefr(String numRefr) {
        this.numRefr = numRefr;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != cliente.id) return false;
        if (nombre != null ? !nombre.equals(cliente.nombre) : cliente.nombre != null) return false;
        if (numRefr != null ? !numRefr.equals(cliente.numRefr) : cliente.numRefr != null) return false;
        if (correo != null ? !correo.equals(cliente.correo) : cliente.correo != null) return false;
        if (empresa != null ? !empresa.equals(cliente.empresa) : cliente.empresa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (numRefr != null ? numRefr.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (empresa != null ? empresa.hashCode() : 0);
        return result;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
