package com.example.request_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txtResult;
    EditText txtUrl;
    Button btnRequest;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtUrl   =(EditText) findViewById(R.id.txtUrl);
        btnRequest = (Button) findViewById(R.id.btnRequest);

        queue = Volley.newRequestQueue(this);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function_request_api();
            }
        });

    }
    public  void function_request_api(){
        try{
            String url  = txtUrl.getText().toString();
             // String url = "http://ws-api.clearview-erp.com/api/t-pos/v1/login?username=pmt-demo-mart&password=123456";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    txtResult.setText(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtResult.setText("That didn't work!");
                    Log.e("error","That didn't work!");
                }
            });
            queue.add(stringRequest);
        }catch (Exception ex){
            Log.e("Error : " ,ex.getMessage());
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (queue != null) {
            queue.cancelAll("");
        }
    }
}