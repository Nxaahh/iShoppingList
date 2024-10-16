package com.example.ishoppinglist.models;

import java.io.Serializable;

public class Producto implements Serializable {

    int id;
    String nombre;
    String nota;
    boolean gluten;
    boolean lactosa;
    Boolean estadoCompra;

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(Boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nota='" + nota + '\'' +
                ", gluten=" + gluten +
                ", lactosa=" + lactosa +
                ", estadoCompra=" + estadoCompra +
                '}';
    }
}
