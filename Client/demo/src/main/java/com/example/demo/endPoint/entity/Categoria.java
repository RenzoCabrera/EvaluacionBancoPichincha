package com.example.demo.endPoint.entity;

import com.example.demo.endPoint.entity.Bien;

import java.util.List;

public class Categoria {

    private Integer idCategoria;

    private String nombre;
    private String estado;

    private List<Bien> bienes;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
    }



    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", bienes=" + bienes +
                '}';
    }
}
