package com.mora.gonzalez.javier.servicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mora.gonzalez.javier.servicioweb.adapter.AdaptadorJSON;
import com.mora.gonzalez.javier.servicioweb.service.Servicio;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDatos extends AppCompatActivity {
    private ListView listaDeLibros;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    private JSONArray jsonArray;
    private AdaptadorJSON miAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        listaDeLibros = findViewById(R.id.listalibros);
        //Servicio servicio = new Servicio(this);
        //servicio.listaDeLibros("http:/tuip:tupuerto/servicioweb/crud/read.php");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://tuip:elpuerto/servicioweb/crud/read.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //jsonArray = response.optJSONArray("libros");
                        //System.out.println("***********************\n"+jsonArray==null+"\n*************************");
                        miAdaptador = new AdaptadorJSON(ActivityDatos.this, response.optJSONArray("libros"), R.layout.libro_adapter);
                        listaDeLibros.setAdapter(miAdaptador);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityDatos.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue = Volley.newRequestQueue(ActivityDatos.this);
        requestQueue.add(jsonObjectRequest);


        /*
        if(servicio.getJsonArray()!=null){
            AdaptadorJSON miAdaptador = new AdaptadorJSON(this, servicio.getJsonArray(), R.layout.libro_adapter);
            listaDeLibros.setAdapter(miAdaptador);
        }else{
            Toast.makeText(this, "No hay libros", Toast.LENGTH_SHORT).show();
        }*/

    }
}
