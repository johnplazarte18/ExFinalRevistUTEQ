package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

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

    private PlaceHolderView mGalleryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGalleryView=(PlaceHolderView)findViewById(R.id.ltrevistas);
        mGalleryView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new GridLayoutManager(this, 3));

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

            revistaAdapter adaptadorRev = new revistaAdapter(getApplicationContext(), lstRevistas);

            mGalleryView.addView(adaptadorRev);



        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}