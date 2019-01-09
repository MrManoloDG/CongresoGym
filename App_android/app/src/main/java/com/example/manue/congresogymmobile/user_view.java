package com.example.manue.congresogymmobile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class user_view extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        txt_name = (TextView) findViewById(R.id.txt_nombre_view);
        txt_plan = (TextView) findViewById(R.id.txt_plan_view);
        txt_tel = (TextView) findViewById(R.id.txt_tel_view);
        txt_email = (TextView) findViewById(R.id.txt_email_view);
        txt_date_start = (TextView) findViewById(R.id.txt_date_start_view);
        txt_date_end = (TextView) findViewById(R.id.txt_date_end_view);
        txt_coment = (TextView) findViewById(R.id.txt_comment_view);
        btn_update = (Button) findViewById(R.id.btn_update_view);
        btn_delete = (Button) findViewById(R.id.btn_delete_view);

        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("persona");

        txt_name.setText(user.getName());
        txt_plan.setText(String.valueOf(user.getName()));
        txt_tel.setText(String.valueOf(user.getTel()));
        txt_email.setText(user.getEmail());
        txt_date_start.setText( new SimpleDateFormat("dd-MM-yyyy").format(user.getDate_start()));
        txt_date_end.setText( new SimpleDateFormat("dd-MM-yyyy").format(user.getDate_end()));
        txt_coment.setText(user.getComment());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_view.this, User_Update.class );
                Bundle b = new Bundle();
                b.putSerializable("persona",  user);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
