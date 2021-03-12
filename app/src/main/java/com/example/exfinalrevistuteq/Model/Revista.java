package com.example.exfinalrevistuteq.Model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exfinalrevistuteq.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@NonReusable
@Layout(R.layout.itemrevista)
public class Revista {
    @View(R.id.imgPortada)
    ImageView imgPortada;

    @View(R.id.txtDescripcion)
    TextView txtDescripcion;

    @View(R.id.txtID)
    TextView txtID;

    private Context mCont;
    private JSONObject itemRevista;


    public Revista(Context context, JSONObject a) throws JSONException {
        this.mCont = context;
        this.itemRevista = a;


    }
    @Resolve
    protected void onResolved(){
        try{
            this.txtDescripcion.setText(this.itemRevista.getString("name"));
            this.txtID.setText(this.itemRevista.getString("journal_id"));
            Glide.with(this.mCont).load(this.itemRevista.getString("portada")).into(this.imgPortada);
        }
        catch (JSONException ex){
            System.out.println(ex.getMessage());
        }
    }
}
