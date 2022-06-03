package com.example.aplikasilaundry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button Login, Register;
    EditText nama,pass;
    String Username,Password;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login=findViewById(R.id.login);
        Register=findViewById(R.id.btnRegist);
        nama=findViewById(R.id.use);
        pass=findViewById(R.id.pwd);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username=nama.getText().toString();
                Password=pass.getText().toString();
                String email="Zulfa";
                String password="12345678";

                if
                (Username.equals(email) && password.equals(password)) {
                    Toast.makeText(getApplicationContext(),"Login Berhasil", Toast.LENGTH_LONG).show();
                    Bundle b = new Bundle();
                    b.putString("a",Username.trim());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtras(b);
                    startActivity(i);
                }
                else if (Username.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email dan Password tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                //Email Salah
                else if (password.equals(password)) {
                    //jika Email salah
                    Toast.makeText(getApplicationContext(), "Email salah", Toast.LENGTH_LONG).show();

                }
                //Password Salah
                else if (Username.equals(email)) {
                    //jika Password salah
                    Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_LONG).show();

                }
                //Email dan Password Salah
                else {
                    //jika email dan password salah
                    Toast.makeText(getApplicationContext(), "Email dan Password Salaj", Toast.LENGTH_LONG).show();
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}