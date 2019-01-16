package com.example.manue.congresogymmobile;


import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class User_View extends Fragment {


    private User user;
    private TextView txt_name;
    private TextView txt_plan;
    private TextView txt_date_start;
    private TextView txt_date_end;
    private TextView txt_tel;
    private TextView txt_email;
    private TextView txt_coment;
    private Button btn_update;
    private Button btn_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_view, container, false);

        txt_name = (TextView) view.findViewById(R.id.txt_nombre_view);
        txt_plan = (TextView) view.findViewById(R.id.txt_plan_view);
        txt_tel = (TextView) view.findViewById(R.id.txt_tel_view);
        txt_email = (TextView) view.findViewById(R.id.txt_email_view);
        txt_date_start = (TextView) view.findViewById(R.id.txt_date_start_view);
        txt_date_end = (TextView) view.findViewById(R.id.txt_date_end_view);
        txt_coment = (TextView) view.findViewById(R.id.txt_comment_view);
        btn_update = (Button) view.findViewById(R.id.btn_update_view);
        btn_delete = (Button) view.findViewById(R.id.btn_delete_view);

        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("persona");

        txt_name.setText(user.getName());
        txt_plan.setText(user.getPlan());
        txt_tel.setText(user.getTel());
        txt_email.setText(user.getEmail());
        txt_date_start.setText( user.getDate_start());
        txt_date_end.setText( user.getDate_end());
        txt_coment.setText(user.getComment());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                User_Create_Update fragment = new User_Create_Update();
                Bundle b = new Bundle();
                b.putSerializable("persona", user);
                fragment.setArguments(b);
                fragmentTransaction.replace(R.id.fragment,fragment);
                fragmentTransaction.commit();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Estas seguro?", Snackbar.LENGTH_LONG)
                        .setAction("Borrar", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new DeleteUserTask().execute();
                            }
                        })
                        .show();
            }
        });

        return view;
    }

    private class DeleteUserTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/users/"+user.getId())
                    .delete()
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
                    fragmentTransaction.addToBackStack(null);
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
