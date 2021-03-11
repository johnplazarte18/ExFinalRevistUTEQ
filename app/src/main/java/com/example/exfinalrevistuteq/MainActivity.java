package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.exfinalrevistuteq.Adaptador.revistaAdapter;
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

    RecyclerView rcvrevistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvrevistas=(RecyclerView) findViewById(R.id.ltrevistas);
        rcvrevistas.setHasFixedSize(true);
        rcvrevistas.setLayoutManager(new LinearLayoutManager(this));
        rcvrevistas.setItemAnimator(new DefaultItemAnimator());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php",
                datos, this, this);
        ws.execute("GET");

    }


    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Revista> lstRevistas = new ArrayList<Revista>();

        try {

            JSONArray JSONRevistas=  new JSONArray(result);

            lstRevistas = Revista.JsonObjectsBuild(JSONRevistas);

            revistaAdapter adaptadorRev = new revistaAdapter(this, lstRevistas);

            rcvrevistas.setAdapter(adaptadorRev);



        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}