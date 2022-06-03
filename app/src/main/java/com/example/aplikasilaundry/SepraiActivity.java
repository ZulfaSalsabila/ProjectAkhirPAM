package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SepraiActivity extends AppCompatActivity {

    Button hitung, kembali;
    EditText berat;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seprai);

        hitung = (Button) findViewById(R.id.hitungs);
        kembali = (Button) findViewById(R.id.kembalis);
        berat = (EditText)findViewById(R.id.berats);
        total = (TextView) findViewById(R.id.totals);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (berat.length()==0){
                    Toast.makeText(getApplication(), "Masukkan berat", Toast.LENGTH_SHORT).show();
                } else {
                    String isiberat = berat.getText().toString();
                    int berat = Integer.parseInt(isiberat);
                    int hb = hargatotal(berat);
                    String output = String.valueOf(hb);
                    total.setText(output.toString());
                }
            }

            private int hargatotal(int berat) {
                return 13000*berat;
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SepraiActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }
}