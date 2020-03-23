package com.example.parcial1am;

import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPeliculas extends RecyclerView.Adapter<AdapterPeliculas.ViewHolderPeliculas> {

    ArrayList<Pelicula> peliculas;


    public AdapterPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public ViewHolderPeliculas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.itme_list,null,false);


        return new ViewHolderPeliculas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPeliculas holder, int position) {
        holder.nombre.setText(peliculas.get(position).getNombre());
        holder.director.setText(peliculas.get(position).getDirector());
        holder.idioma.setText(peliculas.get(position).getIdioma());
        holder.genero.setText(peliculas.get(position).getGenero());

    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }



    public  class ViewHolderPeliculas extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView nombre,director,idioma,genero;
        LinearLayout card;

        public ViewHolderPeliculas(@NonNull View v) {
            super(v);
            nombre= v.findViewById(R.id.IdNombre);
            director= v.findViewById(R.id.Iddirector);
            idioma=v.findViewById(R.id.IdIdioma);
            genero= v.findViewById(R.id.IdGenero);
            card=v.findViewById(R.id.IdLinear);
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Color titulo");
            menu.add(0, 120, 1, "Amarrillo");
            menu.add(0, 121, 2, "Azul");
            menu.add(0, 122, 2, "Rojo");
        }
    }




}


