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

import org.json.JSONException;
import org.json.JSONObject;

@NonReusable
@Layout(R.layout.itemvolumen)
public class Volumen {
    @View(R.id.imgVolumen)
    ImageView imgVolumen;

    @View(R.id.txtTitulo)
    TextView txtTitulo;


    private Context mCont;
    private JSONObject itemVolumen;


    public Volumen(Context context, JSONObject a) throws JSONException {
        this.mCont = context;
        this.itemVolumen = a;


    }
    @Resolve
    protected void onResolved(){
        try{
            this.txtTitulo.setText(this.itemVolumen.getString("title"));
            //this.txtID.setText(this.itemRevista.getString("journal_id"));
            Glide.with(this.mCont).load(this.itemVolumen.getString("cover")).into(this.imgVolumen);
        }
        catch (JSONException ex){
            System.out.println(ex.getMessage());
        }
    }
}
