package com.example.hackillinoischallenge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    ArrayList<Event> list;
    Context context;

    public CustomAdapter(ArrayList<Event> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element, parent, false);
        CustomViewHolder cvh = new CustomViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.event = list.get(position);

        holder.tName.setText(list.get(position).getName());
        holder.tDescription.setText(list.get(position).getDescription());

        ArrayList<String> locations = list.get(position).getLocations();
        if (locations.isEmpty()) {
            holder.tLocation.setText("");
        } else {
            // remove "[" and "]" (ex. "[Siebel 1404]" -> "Siebel 1404")
            holder.tLocation.setText(locations.toString().substring(1, locations.size() - 1));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        Event event;
        public TextView tName;
        public TextView tLocation;
        public TextView tDescription;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            tName = view.findViewById(R.id.id_tName);
            tLocation = view.findViewById(R.id.id_tLocation);
            tDescription = view.findViewById(R.id.id_tDescription);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", "CLICK");

                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("id", event.getId());
                    intent.putExtra("name", event.getName());
                    intent.putExtra("description", event.getDescription());
                    intent.putExtra("startTime", event.getStartDate().toString());
                    intent.putExtra("endTime", event.getEndDate().toString());
                    intent.putExtra("sponsor", event.getSponsor());
                    intent.putExtra("interested", event.isInterested());

                    context.startActivity(intent);
                }
            });
        }
    }
}
