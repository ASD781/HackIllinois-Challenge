package com.example.hackillinoischallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String apiUrl = "https://api.hackillinois.org/event/";
    String jsonString = "";

    TextView tJSON;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tJSON = findViewById(R.id.id_tJSON);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "SUCCESS");
                        jsonString = response;

                        setup();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "ERROR");
                jsonString = "";
            }
        });
        queue.add(stringRequest);
    }

    public void setup() {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray events = jsonObject.getJSONArray("events");
            Log.d("TAG", events.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
