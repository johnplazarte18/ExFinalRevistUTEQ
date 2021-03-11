package com.example.exfinalrevistuteq.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
    private String id;
    private String descripcion;
    private String portada;

    public String getId(){
        return  id;
    }
    public String getDescripcion(){
        return  descripcion;
    }
    public String getPortada(){
        return  portada;
    }



    public Revista(JSONObject a) throws JSONException {
        id =  a.getString("journal_id");
        descripcion =  a.getString("description");
        portada =  a.getString("portada");


    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }
}
