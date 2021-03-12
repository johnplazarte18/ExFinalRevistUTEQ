package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exfinalrevistuteq.Model.Edicion;
import com.example.exfinalrevistuteq.Model.Volumen;
import com.example.exfinalrevistuteq.WebService.Asynchtask;
import com.example.exfinalrevistuteq.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EdicionPublicada extends AppCompatActivity implements Asynchtask {

    PlaceHolderView phv_edicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_publicada);

        Bundle bundle = this.getIntent().getExtras();

        phv_edicion = (PlaceHolderView)findViewById(R.id.phv_edicion);
        phv_edicion.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(3)
                .setLayoutManager(new GridLayoutManager(this, 1));


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/pubs.php?i_id="
                +bundle.getString("IDVolumen"),
                datos, this, this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            this.phv_edicion.addView(new Edicion(getApplicationContext(), jsonObject));
        }
    }
}