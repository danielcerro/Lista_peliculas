package com.example.parcial1am;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ListaActivity extends AppCompatActivity {

    ArrayList<Pelicula> arrayPelicula;
    RecyclerView recycler;
    AdapterPeliculas adapter;
    TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_main);

        arrayPelicula =new ArrayList<>();
        recycler=(RecyclerView) findViewById(R.id.recycler_id);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        Intent i = getIntent();
        arrayPelicula = i.getParcelableArrayListExtra("peliculasArray");
        adapter=new AdapterPeliculas(arrayPelicula);
        recycler.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 120:
                nombre=findViewById(R.id.IdNombre);
                nombre.setTextColor(Color.YELLOW);
                break;
            case 121:
                nombre=findViewById(R.id.IdNombre);
                nombre.setTextColor(Color.BLUE);
                break;
            case 122:
                nombre=findViewById(R.id.IdNombre);
                nombre.setTextColor(Color.RED);
                break;
            case 4:

                nombre.setTextColor(Color.YELLOW);
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuordeng:
                Collections.sort(arrayPelicula, new ComparadorpeliculaG());
                AdapterPeliculas Adaptadorpelicula = new AdapterPeliculas(arrayPelicula);
                recycler.setAdapter(Adaptadorpelicula);
                Toast.makeText(getApplicationContext(),"Orden por genero",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnuordenN:
                Collections.sort(arrayPelicula, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        return o1.getNombre().compareTo(o2.getNombre());
                    }
                });

                Adaptadorpelicula = new AdapterPeliculas(arrayPelicula);
                recycler.setAdapter(Adaptadorpelicula);
                Toast.makeText(getApplicationContext(),"Orden por nombre A-Z",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnuinvertir:
                Collections.reverse(arrayPelicula);
                Adaptadorpelicula = new AdapterPeliculas(arrayPelicula);
                recycler.setAdapter(Adaptadorpelicula);
                Toast.makeText(getApplicationContext(),"lista invertida",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnueliminarfst:
                new AlertDialog.Builder(this).setTitle("Confirmación")
                        .setMessage("Desea eliminar la primera pelicula")
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayPelicula.remove(0);
                                AdapterPeliculas Adaptadorpelicula = new AdapterPeliculas(arrayPelicula);
                                recycler.setAdapter(Adaptadorpelicula);
                                Toast.makeText(getApplicationContext(),"Pelicula eliminada",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Pelicula no eliminada",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
