package com.adteccorp.volleyandglide.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adteccorp.volleyandglide.R;
import com.adteccorp.volleyandglide.adapters.RecyclerviewAdapter;
import com.adteccorp.volleyandglide.model.Anime;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime> animeitems = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = findViewById( R.id.recyclerviewid );
        jsonrequest();
    }

    private void jsonrequest() {
        request = new JsonArrayRequest( JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject( i );
                        Anime anime = new Anime();
                        anime.setName( jsonObject.getString( "name" ) );
                        anime.setDescription( jsonObject.getString( "description" ) );
                        anime.setRating( jsonObject.getString( "Rating" ) );
                        anime.setCategories( jsonObject.getString( "categorie" ) );
                        anime.setEpisode( jsonObject.getInt( "episode" ) );
                        anime.setStudio( jsonObject.getString( "studio" ) );
                        anime.setImage_url( jsonObject.getString( "img" ) );
                        animeitems.add( anime );

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                setuprecyclerview( animeitems );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue = Volley.newRequestQueue( MainActivity.this );
        requestQueue.add( request );

    }

    private void setuprecyclerview(List<Anime> animeitems) {

        RecyclerviewAdapter myadapter = new RecyclerviewAdapter( this, animeitems );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setAdapter( myadapter );

    }
}
