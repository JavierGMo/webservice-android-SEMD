package com.mora.gonzalez.javier.servicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mora.gonzalez.javier.servicioweb.service.Servicio;

public class MainActivity extends AppCompatActivity {
    EditText codigoLibro, tituloLibro, autorLibro, editorialLibro, precioLibro;
    Button btnVerLibros, btnAgregarLibro, btnActualizarlibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Edit text*/

        codigoLibro = findViewById(R.id.edtxtcodigolibro);
        tituloLibro = findViewById(R.id.edtxttitulolibro);
        autorLibro = findViewById(R.id.edtxtautorlibro);
        editorialLibro = findViewById(R.id.edtxteditorial);
        precioLibro = findViewById(R.id.edtxtprecio);

        /*Botones*/

        btnVerLibros = findViewById(R.id.btnverlibros);
        btnAgregarLibro = findViewById(R.id.btnagregarlibro);
        btnActualizarlibros = findViewById(R.id.btneditarlibro);

        /*Acciones de botones*/
        //Ir a actualizar libro
        btnActualizarlibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irAEditarLibro = new Intent(MainActivity.this, ActivityEditarLibro.class);
                startActivity(irAEditarLibro);
            }
        });
        //Agregar libro a MySQL
        btnAgregarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(revisarCampos(codigoLibro) && revisarCampos(tituloLibro) &&
                    revisarCampos(autorLibro) && revisarCampos(editorialLibro) && revisarCampos(precioLibro)){
                    Servicio servicio = new Servicio(MainActivity.this,
                            codigoLibro.getText().toString(),
                            tituloLibro.getText().toString(),
                            autorLibro.getText().toString(),
                            editorialLibro.getText().toString(),
                            precioLibro.getText().toString());
                    servicio.ejecucionDeServicio("http://tpuerto:tupuerto/servicioweb/crud/create.php");

                }else{
                    Toast.makeText(MainActivity.this, "Faltan campos por llenas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVerLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizarDatos = new Intent(MainActivity.this, ActivityDatos.class);
                startActivity(visualizarDatos);
            }
        });

    }
    private boolean revisarCampos(EditText campoAVerificar){
        boolean validado = false;
        if(campoAVerificar != null){
            if(campoAVerificar.getText() != null){
                if(!campoAVerificar.getText().toString().trim().equals("")){
                    validado = true;
                }
            }
        }
        return validado;
    }
}
