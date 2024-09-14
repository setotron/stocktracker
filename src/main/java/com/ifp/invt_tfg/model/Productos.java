package com.ifp.invt_tfg.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Productos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "Color")
    private String color;
    @Basic
    @Column(name = "Tipo")
    private String tipo;
    @Basic
    @Column(name = "NSerie")
    private String nSerie;
    @Basic
    @Column(name = "FechaEntrada")
    private Date fechaEntrada;
    @Basic
    @Column(name = "Imagen")
    private String imagen="no-image.png";

    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuarios usuarios;

    @Basic
    @Column(name = "Unidades")
    private int unidades;
    @Basic
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Basic
    @Column(name = "Precio")
    private float precio;
    @Basic
    @Column(name = "Marca")
    private String marca;
    @Basic
    @Column(name = "Descripcion")
    private String descripcion;

    @Basic
    @Column(name = "Codigo")
    private String codigo;

    public Productos(String nombre, String codigo, float precio, int unidades, int id) {
        this.nombre = nombre;
        this.codigo=codigo;
        this.precio=precio;
        this.unidades=unidades;
        this.id=id;

    }

    public Productos(String nombre, int unidades, float precio, String codigo) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.codigo = codigo;
    }

    public Productos() {

    }

    public boolean sinUnidades(){

        return this.unidades<=0;
    }

    public void restarUnidades(float unidades){
        this.unidades -=unidades;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public Usuarios getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Productos productos = (Productos) o;

        if (id != productos.id) return false;
        if (nombre != null ? !nombre.equals(productos.nombre) : productos.nombre != null) return false;
        if (color != null ? !color.equals(productos.color) : productos.color != null) return false;
        if (tipo != null ? !tipo.equals(productos.tipo) : productos.tipo != null) return false;
        if (nSerie != null ? !nSerie.equals(productos.nSerie) : productos.nSerie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (nSerie != null ? nSerie.hashCode() : 0);
        return result;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }



    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
