package com.magnet;

import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private int num;
    private String nombre;

    private Funcion(int num, String nombre)
    {
        this.num = num;
        this.nombre = nombre;
    }

    public static List<Funcion> crearListaFunciones() {
        List<Funcion> listaFunciones = new ArrayList<>();

        // Añadir funciones a la lista
        listaFunciones.add(new Funcion(1, "Administrador"));
        listaFunciones.add(new Funcion(2, "Cocinero"));
        listaFunciones.add(new Funcion(3, "Mesero"));
        listaFunciones.add(new Funcion(4,"Cajero" ));
        return listaFunciones;
    }

}
