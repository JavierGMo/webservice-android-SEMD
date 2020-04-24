package com.mora.gonzalez.javier.servicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mora.gonzalez.javier.servicioweb.service.Servicio;

public class ActivityEditarLibro extends AppCompatActivity {
    EditText codigoLibroUpd, titutloLibroUpd, autorLibroUpd, editorialLibroUpdt, precioLibroUpd;
    Button btnActualizarLibroOk, btnVerLibrosUpd, btnIrAAgregarLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        /*Edit text de actualizar el libro*/

        codigoLibroUpd = findViewById(R.id.edtxtcodigoupd);
        titutloLibroUpd = findViewById(R.id.edtxttituloupd);
        autorLibroUpd = findViewById(R.id.edtxteditorialupd);
        editorialLibroUpdt = findViewById(R.id.edtxteditorialupd);
        precioLibroUpd = findViewById(R.id.edtxtprecioupd);

        /*Botones para actualizar libros, ver libros e ir a agregar libro*/

        btnActualizarLibroOk = findViewById(R.id.btnactualizarupd);
        btnVerLibrosUpd = findViewById(R.id.btnverlibroupd);
        btnIrAAgregarLibro = findViewById(R.id.btnagregarlibupdt);

        //Actualizar libros

        btnActualizarLibroOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(revisarCampos(codigoLibroUpd) && revisarCampos(titutloLibroUpd) &&
                    revisarCampos(autorLibroUpd) && revisarCampos(editorialLibroUpdt) && revisarCampos(precioLibroUpd)){
                    //Aqui va el webservice
                    if(revisarCampos(codigoLibroUpd) && revisarCampos(titutloLibroUpd) &&
                            revisarCampos(autorLibroUpd) && revisarCampos(editorialLibroUpdt) && revisarCampos(precioLibroUpd)){
                        Servicio servicio = new Servicio(ActivityEditarLibro.this,
                                codigoLibroUpd.getText().toString(),
                                titutloLibroUpd.getText().toString(),
                                autorLibroUpd.getText().toString(),
                                editorialLibroUpdt.getText().toString(),
                                precioLibroUpd.getText().toString());
                        servicio.ejecucionDeServicio("http://tuip:tupuerto/servicioweb/crud/update.php");

                    }else{
                        Toast.makeText(ActivityEditarLibro.this, "Faltan campos por llenas", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        //Ir a agregar libro

        btnIrAAgregarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irAAgregarLibro = new Intent(ActivityEditarLibro.this, MainActivity.class);
                startActivity(irAAgregarLibro);
            }
        });
        btnVerLibrosUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizarDatos = new Intent(ActivityEditarLibro.this, ActivityDatos.class);
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
