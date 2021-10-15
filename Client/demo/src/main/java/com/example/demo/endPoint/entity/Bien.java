package com.example.demo.endPoint.entity;

public class Bien {

    private Integer idBien;

    private String nombre;

    private Integer idCategoria;

    private String estado;

    //@JsonIgnore
    private Categoria categoria;


    public Integer getIdBien() {
        return idBien;
    }

    public void setIdBien(Integer idBien) {
        this.idBien = idBien;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Bien{" +
                "idBien=" + idBien +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria +
                ", estado='" + estado + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
