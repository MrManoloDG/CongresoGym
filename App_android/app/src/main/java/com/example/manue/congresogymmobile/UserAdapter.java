package com.example.manue.congresogymmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> users;
    private Context context;
    private MainActivity mainActivity;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView tel;
        TextView email;
        TextView plan;
        CardView card;

        public MyViewHolder(View v) {
            super(v);
            tel = (TextView) v.findViewById(R.id.tel);
            name = (TextView) v.findViewById(R.id.name);
            email = (TextView) v.findViewById(R.id.email);
            plan = (TextView) v.findViewById(R.id.plan);
            card = (CardView) v.findViewById(R.id.cv);
        }
    }

    public UserAdapter(ArrayList<User> myDataset,MainActivity ma) {
        users = myDataset;
        mainActivity = ma;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tel.setText(String.valueOf(users.get(position).getTel()));
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.plan.setText(String.valueOf(users.get(position).getPlan()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "You've clicked on " + users.get(position).getName();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(mainActivity, user_view.class );
                Bundle b = new Bundle();
                b.putSerializable("persona", (Serializable) users.get(position));
                intent.putExtras(b);
                mainActivity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
