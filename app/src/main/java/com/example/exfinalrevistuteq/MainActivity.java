package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exfinalrevistuteq.Model.Revista;
import com.example.exfinalrevistuteq.WebService.Asynchtask;
import com.example.exfinalrevistuteq.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    PlaceHolderView phvGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phvGallery = (PlaceHolderView)findViewById(R.id.phv_gallery);
        phvGallery.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(3)
                .setLayoutManager(new GridLayoutManager(this, 1));


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php",
                datos, this, this);
        ws.execute("GET");

    }
    public void btEnviar(View view){
        Intent intent = new Intent(MainActivity.this, VolumenPublicado.class);
        TextView txtID = (TextView) findViewById(R.id.txtID);
        Bundle b = new Bundle();
        b.putString("IDRevista", txtID.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }


    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            this.phvGallery.addView(new Revista(getApplicationContext(), jsonObject));
        }

    }
}