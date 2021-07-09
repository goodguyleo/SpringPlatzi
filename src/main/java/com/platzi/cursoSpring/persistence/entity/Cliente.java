package com.platzi.cursoSpring.persistence.entity;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    private String id;
    private String nombre;

    @Column(name = "apellidos")
    private String apellido;
    private Double celular;
    private String direccion;

    @Column(name = "correo_electronico")
    private String crreoElectronico;

    @OneToMany(mappedBy =  "cliente")
    private List<Compra> compras;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Double getCelular() {
        return celular;
    }

    public void setCelular(Double celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCrreoElectronico() {
        return crreoElectronico;
    }

    public void setCrreoElectronico(String crreoElectronico) {
        this.crreoElectronico = crreoElectronico;
    }
}
