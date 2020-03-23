package com.example.parcial1am;

import java.util.Comparator;

public class ComparadorNombre implements Comparator<Pelicula> {
    public int compare(Pelicula a, Pelicula b) {
        return a.getNombre().toString().compareTo(b.getGenero().toString());
    }
}
