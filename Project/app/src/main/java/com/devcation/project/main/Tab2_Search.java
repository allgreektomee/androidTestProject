package com.devcation.project.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.devcation.project.R;


public class Tab2_Search extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab2_search, container, false);

        WebView webview = rootView.findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

            }
        });


        webview.loadUrl("http://www.naver.com");
        //manifests
        //android:usesCleartextTraffic="true"
        //<uses-permission android:name="android.permission.INTERNET"/>

        return rootView;
    }

}
