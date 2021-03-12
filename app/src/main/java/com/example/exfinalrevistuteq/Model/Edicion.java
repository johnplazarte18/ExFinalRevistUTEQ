package com.example.exfinalrevistuteq.Model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exfinalrevistuteq.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import org.json.JSONException;
import org.json.JSONObject;


@NonReusable
@Layout(R.layout.itemedicion)
public class Edicion {


    @View(R.id.txtEdicionTitulo)
    TextView txtEdicionTitulo;

    @View(R.id.txtPDF)
    TextView txtPDF;

    @View(R.id.txtHtml)
    TextView txtHtml;

    private Context mCont;
    private JSONObject itemEdicion;


    public Edicion(Context context, JSONObject a) throws JSONException {
        this.mCont = context;
        this.itemEdicion = a;


    }
    @Resolve
    protected void onResolved(){
        try{
            this.txtEdicionTitulo.setText(this.itemEdicion.getString("title"));

            this.txtPDF.setText("PDF "+itemEdicion.getJSONArray("galeys").getJSONObject(0).getString("UrlViewGalley"));
            this.txtHtml.setText("HMTL "+itemEdicion.getJSONArray("galeys").getJSONObject(1).getString("UrlViewGalley"));

        }
        catch (JSONException ex){
            System.out.println(ex.getMessage());
        }
    }
}
