package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginDatactivity extends AppCompatActivity {

    private Button login,registrasi;
    private EditText nama, pass;
    private String username, password;

    private static String URL_Loign = "https://20200140013.praktikumtiumy.com/TA/login.php";
    private static final String TAG = LoginDatactivity.class.getSimpleName();
    private static final String TAG_SUCCES = "success";
    int sukses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_datactivity);

        login = findViewById(R.id.btn_login);
        registrasi = findViewById(R.id.btn_registrasi);
        nama = findViewById(R.id.lgNama);
        pass = findViewById(R.id.lgPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logiin();
            }
        });

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    private void logiin() {
        username = nama.getText().toString();
        password = pass.getText().toString();

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            final StringRequest loginData = new StringRequest(Request.Method.POST, URL_Loign, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, "Respon : " + response.toString());

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        sukses = jsonObject.getInt(TAG_SUCCES);
                        if(sukses == 1){
                            Toast.makeText(LoginDatactivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(main);
                        }
                        else {
                            Toast.makeText(LoginDatactivity.this, "Login GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error : " + error.getMessage());

                    Toast.makeText(LoginDatactivity.this, "GAGAL Login", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();

                    params.put("username",username);
                    params.put("password", password);

                    return params;
                }
            };
            requestQueue.add(loginData);
        }
    }
}