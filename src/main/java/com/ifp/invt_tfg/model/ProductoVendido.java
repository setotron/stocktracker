package com.ifp.invt_tfg.model;

import java.time.LocalDate;

public class ProductoVendido extends Productos{

    private Float cantidad;


    public ProductoVendido( String nombre,String codigo, float precio, int unidades,int id,Float cantidad) {
        super(nombre,codigo,precio,unidades,id);
        this.cantidad = cantidad;
    }

    public ProductoVendido(String nombre, String codigo, float precio, int unidades, Float cantidad) {
        super(nombre,unidades,precio,codigo);
        this.cantidad = cantidad;
    }

    public void InputCantidad(float unid){
        this.cantidad=Float.valueOf(unid);
    }

    public void restarCantidad(){
        this.cantidad--;
    }

    public void aumentarCantidad(){
        this.cantidad++;
    }

    public Float getCantidad(){
        return cantidad;
    }

    public Float getTotal() {
        return this.getPrecio() * this.cantidad;
    }


}
