package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exfinalrevistuteq.Model.Revista;
import com.example.exfinalrevistuteq.Model.Volumen;
import com.example.exfinalrevistuteq.WebService.Asynchtask;
import com.example.exfinalrevistuteq.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolumenPublicado extends AppCompatActivity implements Asynchtask {
    PlaceHolderView phv_volumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumenes_publicados);

        Bundle bundle = this.getIntent().getExtras();

        phv_volumen = (PlaceHolderView)findViewById(R.id.phv_volumen);
        phv_volumen.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(3)
                .setLayoutManager(new GridLayoutManager(this, 1));


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+bundle.getString("IDRevista"),
                datos, this, this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            this.phv_volumen.addView(new Volumen(getApplicationContext(), jsonObject));
        }
    }
    public void btEnviarEdicion(View view){
        Intent intent = new Intent(VolumenPublicado.this, EdicionPublicada.class);
        TextView txtID = (TextView) findViewById(R.id.txtIDVolumen);
        Bundle b = new Bundle();
        b.putString("IDVolumen", txtID.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }
}