package com.ifp.invt_tfg.model;

import jakarta.persistence.*;

@Entity
public class Pedidos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    public Pedidos(Float cantidad, String codigo, String nombre, Float precio, Usuarios usuarios,Ventas v) {
        this.cantidad=cantidad;
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
        this.usuarios=usuarios;
        this.ventas=v;

    }



    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "Cantidad")
    private float cantidad;
    @Basic
    @Column(name = "Precio")
    private float precio;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "Codigo")
    private String codigo;

    public Pedidos(Float cantidad, Float precio, String nombre, String codigo) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre= nombre;
        this.codigo = codigo;

    }

    public Pedidos() {

    }


    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Float getTotal() {
        return this.cantidad * this.precio;
    }

    @ManyToOne
    @JoinColumn
    private Ventas ventas;

    @OneToOne
    @JoinColumn(name="idUsu")
    private Usuarios usuarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedidos pedidos = (Pedidos) o;

        if (id != pedidos.id) return false;
        if (Double.compare(pedidos.cantidad, cantidad) != 0) return false;
        if (Double.compare(pedidos.precio, precio) != 0) return false;
        if (nombre != null ? !nombre.equals(pedidos.nombre) : pedidos.nombre != null) return false;
        if (codigo != null ? !codigo.equals(pedidos.codigo) : pedidos.codigo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(cantidad);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        return result;
    }
}
