package com.example.hackillinoischallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String apiUrl = "https://api.hackillinois.org/event/";
    private String jsonString = "";

    ArrayList<Event> eventList;

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        rv = findViewById(R.id.id_rv);
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

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

            eventList = new ArrayList<>();
            for (int i = 0; i < events.length(); i++) {
                JSONObject current = events.getJSONObject(i);
                eventList.add(new Event(current.getString("id"), current.getString("name"),
                        current.getString("description"), current.getString("sponsor"), current.getString("eventType"),
                        current.getJSONArray("locations"), current.getLong("startTime")*1000, current.getLong("endTime")*1000));
            }
            // Log.d("TAG", eventList.toString());
            Collections.sort(eventList, new EventComparator());
            Log.d("TAG", eventList.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new CustomAdapter(eventList);
        rv.setAdapter(adapter);
    }
}
