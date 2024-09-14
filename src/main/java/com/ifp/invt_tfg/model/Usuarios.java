package com.ifp.invt_tfg.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


@Entity
public class Usuarios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Nombre")
    private String nombre;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "FechaRegistro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;
    @Basic
    @Column(name = "Status")
    private int status;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="RolesUsuarios",
            joinColumns = @JoinColumn(name="idUsuarios"),
            inverseJoinColumns = @JoinColumn(name="idRoles")
            )
    private Set<Roles> roles = new HashSet<>();

    public void agregar(Roles tmpRoles) {
        if (roles == null) {
            roles = (Set<Roles>) new LinkedList<Roles>();
        }
        roles.add(tmpRoles);

    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

/* @OneToMany
    @JoinTable(name="RolesUsuarios",
            joinColumns = @JoinColumn(name="idRoles"),
            inverseJoinColumns = @JoinColumn(name="idUsuarios")
    )
    private Roles rol;

    public Integer getRol() {


        Integer id=rol.getId();
        System.out.println("AAAAAAAAAAAQQQQQQQQQUIIIIIIIIIIIIIIIIII "+rol.getId());
        return id;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    @OneToOne
    @JoinTable(name="RolesUsuarios",
            joinColumns = @JoinColumn(name="idUsuarios"),
            inverseJoinColumns = @JoinColumn(name="idRoles")
    )*/
    /*private Rolesusuarios RLUS;

    public String getRLUS() {
        String Admin="ADMIN";
        String Gerente="GERENTE";
        String Emp="EMPLEADO";
        String nll="";
        if(RLUS.getIdRoles()==1){
            return Admin;
        }else if(RLUS.getIdRoles()==2){
            return Gerente;

        }else if(RLUS.getIdRoles()==3){
            return Emp;
        }
        return nll;
    }

    public void setRLUS(Rolesusuarios RLUS) {
        this.RLUS = RLUS;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuarios usuarios = (Usuarios) o;

        if (id != usuarios.id) return false;
        if (status != usuarios.status) return false;
        if (username != null ? !username.equals(usuarios.username) : usuarios.username != null) return false;
        if (password != null ? !password.equals(usuarios.password) : usuarios.password != null) return false;
        if (nombre != null ? !nombre.equals(usuarios.nombre) : usuarios.nombre != null) return false;
        if (email != null ? !email.equals(usuarios.email) : usuarios.email != null) return false;
        if (fechaRegistro != null ? !fechaRegistro.equals(usuarios.fechaRegistro) : usuarios.fechaRegistro != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
