package com.example.manue.congresogymmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Localizacion extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.localizacion, container, false);
        WebView myWebView = view.findViewById(R.id.map);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.com/maps/place/Complejo+Deportivo/@36.52865,-6.2119299,18z/data=!4m5!3m4!1s0xd0dcddc00bacbcb:0x2c9500fa5e5dfdb6!8m2!3d36.5285648!4d-6.2110949d0dcddc00bacbcb:0x2c9500fa5e5dfdb6!8m2!3d36.5285648!4d-6.2110949");
        return view;
    }
}


