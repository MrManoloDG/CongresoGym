package com.example.manue.congresogymmobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class User_List extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                User_Create_Update fragment = new User_Create_Update();
                fragmentTransaction.replace(R.id.fragment,fragment);
                fragmentTransaction.commit();
            }
        });

        // Referenciamos al RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        // Mejoramos rendimiento con esta configuración
        mRecyclerView.setHasFixedSize(true);
        // Creamos un LinearLayoutManager para gestionar el item.xml creado antes
        mLayoutManager = new LinearLayoutManager(getContext());
        // Lo asociamos al RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);
        users = new ArrayList<User>();
        new LongRunningGetIO().execute();
        // Inflate the layout for this fragment
        return view;
    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/users")
                    .build();
            try {
                Response res = client.newCall(request).execute();
                text = res.body().string();
            } catch (Exception e) {
                return e.toString();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String results) {
            if (results != null) {
                JSONArray respJSON = null;
                // Creamos un UserAdapter pasándole todos nuestro Usuarios
                mAdapter = new UserAdapter(users);
                // Asociamos el adaptador al RecyclerView
                mRecyclerView.setAdapter(mAdapter);
                try{
                    respJSON = new JSONArray(results);
                    for(int i=0;i<respJSON.length();i++) {

                        JSONObject json = respJSON.getJSONObject(i);
                        String id = json.getString("_id");
                        String name = json.getString("name");
                        String email = json.getString("email");
                        String plan = json.getString("plan");
                        String telf = json.getString("telf");
                        String comment = json.getString("comment");
                        String date_start = json.getString("date_start");
                        String date_end = json.getString("date_end");
                        users.add(new User( id, name, plan, date_start, date_end, telf, email, comment));

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}