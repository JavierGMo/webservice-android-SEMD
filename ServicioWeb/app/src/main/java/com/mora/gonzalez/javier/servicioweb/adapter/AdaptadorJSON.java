package com.mora.gonzalez.javier.servicioweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mora.gonzalez.javier.servicioweb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdaptadorJSON extends BaseAdapter {
    private Context contexto;
    //private ArrayList<String[]> dataLibros;
    private JSONArray dataLibros;
    private int layout;

    public AdaptadorJSON(Context contexto, JSONArray dataLibros, int layout){
        this.contexto = contexto;
        this.dataLibros = dataLibros;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        int count = 0;
        if(dataLibros!=null){
            count = dataLibros.length();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {

        try {
            return dataLibros.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vistaPersonalizada = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.contexto);
        vistaPersonalizada = layoutInflater.inflate(R.layout.libro_adapter, null);
        /*
        JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                System.out.println("*************\n" + jsonObject.optString("titulo") + "\n*******************");
                 datos.add(new String[]{
                         jsonObject.optString("codigo"),
        */

        JSONObject jsonObject = null;
        try {
            jsonObject = dataLibros.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String codigo = jsonObject!=null?jsonObject.optString("codigo"):"#";
        String titulo = jsonObject!=null?jsonObject.optString("titulo"):"#";
        String autor = jsonObject!=null?jsonObject.optString("autor"):"#";
        String editorial = jsonObject!=null?jsonObject.optString("editorial"):"#";
        String precio = jsonObject!=null?jsonObject.optString("precio"):"#";
        TextView codigoTxtV = vistaPersonalizada.findViewById(R.id.txtvcodigo),
                tituloTxtV = vistaPersonalizada.findViewById(R.id.txtvtitulo),
                autorTxtV = vistaPersonalizada.findViewById(R.id.txtvautor),
                editorialTxtV = vistaPersonalizada.findViewById(R.id.txtveditorial),
                precioTxtV = vistaPersonalizada.findViewById(R.id.txtvprecio);

        codigoTxtV.setText(codigo);
        tituloTxtV.setText(titulo);
        autorTxtV.setText(autor);
        editorialTxtV.setText(editorial);
        precioTxtV.setText(precio);

        return vistaPersonalizada;
    }
}
