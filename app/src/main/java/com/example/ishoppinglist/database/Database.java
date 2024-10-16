package com.example.ishoppinglist.database;

import com.example.ishoppinglist.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Producto> productoList;

    public static void inicializeList() {
        if (productoList == null) {
            productoList = new ArrayList<>();
            //Producto 1
            Producto p = new Producto();
            p.setId(1);
            p.setNombre("Botella de Agua");
            p.setNota("Botella fria 250ml");

            p.setEstadoCompra(true);
            productoList.add(p);
            //Producto 2
            Producto p2 = new Producto();
            p2.setId(2);
            p2.setNombre("Botella de Fanta");
            p2.setNota("Botella fria 500ml");
            p2.setEstadoCompra(false);

            productoList.add(p2);
        }

    }
}
