package com.sortscript.shebeauty.ui.privancypolicy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.sortscript.shebeauty.R;
public class PolicyFragment extends Fragment {

    WebView simpleWebView;

    private PolicyViewModel policyViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        policyViewModel = new ViewModelProvider(this).get(PolicyViewModel.class);
        View v = inflater.inflate(R.layout.fragment_policy, container, false);


        simpleWebView = v.findViewById(R.id.webview);

// set web view client


        simpleWebView.setWebViewClient(new MyWebViewClient());

// string url which you have to load into a web view
        String url = "https://www.privacypolicies.com/live/85e77e7e-b14f-4e8b-bb0a-695696856ebb";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url);



        return v;
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url
            return true;
        }}}