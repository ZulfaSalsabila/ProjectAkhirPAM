package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button pakaian, seprai, ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pakaian = (Button) findViewById(R.id.pakaian);
        seprai = (Button) findViewById(R.id.seprai);
        ps = (Button) findViewById(R.id.pakaianseprai);

        pakaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pkaian = new Intent(MainActivity.this, PakaianActivity.class);
                startActivity(pkaian);
                finish();
            }
        });
        seprai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sprai = new Intent(MainActivity.this, SepraiActivity.class);
                startActivity(sprai);
                finish();
            }
        });
        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pks = new Intent(MainActivity.this, PakaianSepraiActivity.class);
                startActivity(pks);
                finish();
            }
        });
    }
}