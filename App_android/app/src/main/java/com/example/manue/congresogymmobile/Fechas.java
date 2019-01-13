package com.example.manue.congresogymmobile;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Fechas extends Fragment {

    private ImageButton BtnInscripcion;
    private ImageButton BtnPresentacion;
    private ImageButton BtnCongreso;
    private int NOTIF_ALERTA_ID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fechas, container, false);

        BtnInscripcion = view.findViewById(R.id.bInscripcion);

        BtnInscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Date inscripcionDate = new Date("10/29/2018");
                Date currentTime = Calendar.getInstance().getTime();

                if(inscripcionDate.before(currentTime)){
                    int dias=(int) ((currentTime.getTime()-inscripcionDate.getTime())/86400000);
                    Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),
                            "Han pasado "+Integer.toString(dias)+" dias desde que se produjo la fecha de inscrición, disculpe las  molestia.", Toast.LENGTH_SHORT);
                    toast1.show();
                }else{
                    Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),
                            "Todavía estas a tiempo de inscribirte", Toast.LENGTH_SHORT);
                    toast1.show();
                }


            }

        });

        BtnPresentacion = view.findViewById(R.id.bPresentacion);

        BtnPresentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "¿Quieres saber cuanto falta?", Snackbar.LENGTH_LONG)
                        .setAction("Dias restante", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Date presentacionDate = new Date("01/19/2019");
                                Date currentTime = Calendar.getInstance().getTime();
                                int dias=(int) ((currentTime.getTime()-presentacionDate.getTime())/86400000);
                                if (dias>0){
                                    Toast toast2 = Toast.makeText(getActivity().getApplicationContext(),
                                            "Han pasado "+Integer.toString(dias)+" dias desde que se produjo la fecha de inscrición", Toast.LENGTH_SHORT);
                                    toast2.show();
                                }else{
                                    dias = dias*(-1);
                                    Toast toast2 = Toast.makeText(getActivity().getApplicationContext(),
                                            "faltan "+Integer.toString(dias), Toast.LENGTH_SHORT);
                                    toast2.show();
                                }


                            }
                        })
                        .show();
            }
        });

        BtnCongreso = view.findViewById(R.id.bCongreso);

        BtnCongreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notificacion =
                        new NotificationCompat.Builder(getContext(), "default")
                                .setSmallIcon(R.mipmap.ic_launcher_gym)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_gym))
                                .setContentTitle("Veo que estas decidido")
                                .setContentText("Pulsa para ver donde se celebrará.")
                                .setContentInfo("4")
                                .setTicker("Alerta!");

                Intent fragmento = new Intent(getContext(), MainActivity.class);
                fragmento.putExtra("menuFragment", "2");
                    PendingIntent contIntent =
                      PendingIntent.getActivity(getContext(), 0, fragmento, 0);

                notificacion.setContentIntent(contIntent);

                NotificationManager mNotificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                // Since android Oreo notification channel is needed.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("default",
                            "Default channel",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }

                    mNotificationManager.notify(NOTIF_ALERTA_ID, notificacion.build());

            }
        });


        return view;
    }
}


