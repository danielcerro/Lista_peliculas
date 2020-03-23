package com.example.parcial1am;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    Spinner spinner;
    FloatingActionButton add;
    EditText ednombrep;
    EditText eddirectorp;
    RadioButton rbidioma;
    RadioGroup rbg;
    ArrayList<Pelicula> peliculas ;

    String genero,idioma;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        peliculas=new ArrayList<>();
        ednombrep =(EditText) findViewById(R.id.ednombre);
        eddirectorp =(EditText) findViewById(R.id.eddirector);
        rbg =(RadioGroup) findViewById(R.id.rdg);
        spinner = (Spinner) findViewById(R.id.spngenero);
        //añade elementos al spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.generos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        //lee el dato seleccionado en el spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genero = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbespañol){
                    idioma="Español";
                }else if (checkedId==R.id.rbingles){
                    idioma="Ingles";
                }
            }
        });
        //escucha el evento del boton flotante de agregar
        add= findViewById(R.id.fbtnagregar);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostratDialogoGuardad();
            }
        });

    }


    private void mostratDialogoGuardad() {
        new AlertDialog.Builder(this).setTitle("Confirmación")
                .setMessage("Desea agregar esa pelicula")
                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            peliculas.add(new Pelicula(ednombrep.getText().toString(), eddirectorp.getText().toString(), idioma, genero));
                            ednombrep.setText("");
                            eddirectorp.setText("");
                        Toast.makeText(getApplicationContext(),"Pelicula agregada exitosamente",Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Pelicula no agregada",Toast.LENGTH_LONG).show();

            }
        }).show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnulistado:
                Intent i = new Intent(getApplicationContext(),ListaActivity.class);
                i.putParcelableArrayListExtra("peliculasArray", peliculas);
                startActivity(i);
                break;
            case R.id.mnumayu:
                ednombrep.setText(ednombrep.getText().toString().toUpperCase());
                eddirectorp.setText(eddirectorp.getText().toString().toUpperCase());
                idioma.toUpperCase();
                genero.toUpperCase();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


}
