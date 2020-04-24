package com.mora.gonzalez.javier.servicioweb.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servicio implements Response.ErrorListener, Response.Listener<JSONObject> {
    private Context contexto;
    private String codigo, titutlo, autor, editorial, precio;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    private ArrayList<String[]> datos;
    private JSONArray jsonArray;

    public Servicio(Context contexto){
        this.contexto = contexto;
    }
    public Servicio(Context contexto, String codigo, String titutlo, String autor, String editorial, String precio){
        this.contexto = contexto;
        this.codigo = codigo;
        this.titutlo = titutlo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
    }
    public void ejecucionDeServicio(String URL){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(contexto, "Exito", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String>  getParams() throws AuthFailureError{
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("Content-Type", "application/x-www-form-urlencoded");
                        parametros.put("codigo", codigo);
                        parametros.put("titulo", titutlo);
                        parametros.put("autor", autor);
                        parametros.put("editorial", editorial);
                        parametros.put("precio", precio);
                        return parametros;
                    }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        //envamos la solicitud
        requestQueue.add(stringRequest);
    }
    public void listaDeLibros(String url){
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            jsonArray = response.optJSONArray("libros");
                        }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue = Volley.newRequestQueue(this.contexto);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.contexto, "Error: "+error.toString(), Toast.LENGTH_SHORT).show();
        System.out.println("*************\n" + error.toString() + "\n*******************");
    }

    @Override
    public void onResponse(JSONObject response) {
        this.jsonArray = response.optJSONArray("libros");
        /*datos = new ArrayList<String[]>();
        try {
            System.out.println(response == null);
            System.out.println(json.length());

            for (int i = 0; i<json.length(); ++i){
                //System.out.println("Tamanio: " + response.getString("codigo"));
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                System.out.println("*************\n" + jsonObject.optString("titulo") + "\n*******************");
                 datos.add(new String[]{
                         jsonObject.optString("codigo"),
                         jsonObject.optString("titulo"),
                         jsonObject.optString("autor"),
                         jsonObject.optString("editorial"),
                         jsonObject.optString("precio")
                 });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
    public JSONArray getJsonArray(){
        return this.jsonArray;
    }
}
