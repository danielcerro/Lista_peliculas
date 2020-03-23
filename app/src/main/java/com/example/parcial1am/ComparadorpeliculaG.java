package com.example.parcial1am;

import java.util.Comparator;

public class ComparadorpeliculaG implements Comparator<Pelicula> {
    public int compare(Pelicula a, Pelicula b) {
        return a.getGenero().compareTo(b.getNombre());
    }
}
