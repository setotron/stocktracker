package com.ifp.invt_tfg.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;


import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Entity
public class Ventas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "Fecha")
    private Date fecha;

    @OneToMany(mappedBy = "ventas")
    private Set<Pedidos> PedidosPro;


    @Basic
    @Column(name = "IdCliente")
    private Integer idCliente;

    @OneToOne
    @JoinColumn(name="IdCliente",insertable=false, updatable=false)
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public String getEmpresa(){
        String empresa = cliente.getEmpresa();
        return empresa;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ventas(Cliente C) {
        this.idCliente=C.getId();
    }

    public Ventas() {

    }

    @PrePersist
    void onPrePersist() {fecha = new Date();}

    public Set<Pedidos> getPedidosPro() {

        return PedidosPro;
    }

    public void setPedidosPro(Set<Pedidos> pedidosPro) {
        PedidosPro = pedidosPro;
    }

    public Float getTotal() {
        Float total = 0f;
        for (Pedidos Ped : this.PedidosPro) {
            total += Ped.getTotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ventas ventas = (Ventas) o;

        if (id != ventas.id) return false;
        if (fecha != null ? !fecha.equals(ventas.fecha) : ventas.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
