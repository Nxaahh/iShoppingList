package com.example.ishoppinglist.models;

import java.io.Serializable;

public class Producto implements Serializable {

    int id;
    String nombre;
    String nota;
    Boolean estadoCompra;

    public Producto() {
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Boolean getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(Boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nota='" + nota + '\'' +
                ", estadoCompra=" + estadoCompra +
                '}';
    }
}
