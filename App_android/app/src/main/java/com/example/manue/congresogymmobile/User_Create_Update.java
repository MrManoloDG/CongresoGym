package com.example.manue.congresogymmobile;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
                if(user != null){
                    String user_id = user.getId();
                    if(user_id != null){
                        new EditarUserTask().execute();
                    }
                }else {
                    new CreateUserTask().execute();
                }

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



    private class CreateUserTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;

            final MediaType JSON
                    = MediaType.get("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", txt_name.getText());
                jsonObject.put("plan", txt_plan.getText());
                jsonObject.put("date_start", txt_date_start.getText());
                jsonObject.put("date_end", txt_date_end.getText());
                jsonObject.put("email", txt_email.getText());
                jsonObject.put("telf", txt_tel.getText());
                jsonObject.put("comment", txt_coment.getText());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json = jsonObject.toString();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/users")
                    .post(body)
                    .build();
            try{
                Response response = client.newCall(request).execute();
                text = response.body().string();


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
                    String title = respJSON.getString("msg");
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getContext(), title, duration);
                    toast.show();

                    FragmentManager fragmentManager;
                    FragmentTransaction fragmentTransaction;
                    fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    User_List fragment = new User_List();
                    fragmentTransaction.replace(R.id.fragment,fragment);
                    fragmentTransaction.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private class EditarUserTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;

            final MediaType JSON
                    = MediaType.get("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", txt_name.getText());
                jsonObject.put("plan", txt_plan.getText());
                jsonObject.put("date_start", txt_date_start.getText());
                jsonObject.put("date_end", txt_date_end.getText());
                jsonObject.put("email", txt_email.getText());
                jsonObject.put("telf", txt_tel.getText());
                jsonObject.put("comment", txt_coment.getText());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json = jsonObject.toString();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/users/"+user.getId())
                    .put(body)
                    .build();
            try{
                Response response = client.newCall(request).execute();
                text = response.body().string();
            } catch (Exception e) {
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getContext(), "Se ha producido un error", duration);
                toast.show();
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
                    String title = respJSON.getString("msg");
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getContext(), title, duration);
                    toast.show();

                    FragmentManager fragmentManager;
                    FragmentTransaction fragmentTransaction;
                    fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    User_List fragment = new User_List();
                    fragmentTransaction.replace(R.id.fragment,fragment);
                    fragmentTransaction.commit();
                }catch (Exception e){
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getContext(), "Se ha producido un error", duration);
                    toast.show();
                    e.printStackTrace();
                }
            }
        }
    }

}
