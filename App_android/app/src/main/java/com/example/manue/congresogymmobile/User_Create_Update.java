package com.example.manue.congresogymmobile;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class User_Create_Update extends android.support.v4.app.Fragment {

    private User user;
    private EditText txt_name;
    private EditText txt_plan;
    private EditText txt_date_start;
    private EditText txt_date_end;
    private EditText txt_tel;
    private EditText txt_email;
    private EditText txt_coment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_user__update, container, false);



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        txt_name = (EditText) view.findViewById(R.id.txt_nombre_view);
        txt_plan = (EditText) view.findViewById(R.id.txt_plan_view);
        txt_tel = (EditText) view.findViewById(R.id.txt_tel_view);
        txt_email = (EditText) view.findViewById(R.id.txt_email_view);
        txt_date_start = (EditText) view.findViewById(R.id.txt_date_start_view);
        txt_date_end = (EditText) view.findViewById(R.id.txt_date_end_view);
        txt_coment = (EditText) view.findViewById(R.id.txt_comment_view);

        try {
            Bundle bundle = this.getArguments();
            user = (User) bundle.getSerializable("persona");
            txt_name.setText(user.getName());
            txt_plan.setText(user.getPlan());
            txt_tel.setText(user.getTel());
            txt_email.setText(user.getEmail());
            txt_date_start.setText( user.getDate_start());
            txt_date_end.setText( user.getDate_end());
            txt_coment.setText(user.getComment());
        }catch (Exception e){

        }




        return view;
    }



    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/todos/1")
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
                JSONObject respJSON = null;
                try{
                    respJSON = new JSONObject(results);
                    String title = respJSON.getString("title");
                    //txtResult.setText(title);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
