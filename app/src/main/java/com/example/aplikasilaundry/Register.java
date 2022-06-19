package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {

    private EditText Nama, Username, Password;
    private Button register;

    private String nma, user, pass;

    private static String URL_Register ="https://20200140013.praktikumtiumy.com/TA/createUser.php";
    private static final String TAG = Register.class.getSimpleName();
    private static final String TAG_SUCCESS= "success";
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Nama = findViewById(R.id.rgNama);
        Username = findViewById(R.id.rgUsername);
        Password = findViewById(R.id.rgPassword);
        register = findViewById(R.id.rgBtnRegister);

        //Button login
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginDatactivity.class);
                startActivity(intent);
                simpanData();
            }
        });

    }

    private void simpanData() {
        nma = Nama.getText().toString();
        user = Username.getText().toString();
        pass = Password.getText().toString();

        if (nma.isEmpty() || user.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Data Harus Di isi Semua", Toast.LENGTH_SHORT).show();
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Register, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        success = jsonObject.getInt(TAG_SUCCESS);

                        if (success == 1){
                            Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Register.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();

                    params.put("nama", nma);
                    params.put("username", user);
                    params.put("password", pass);

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}