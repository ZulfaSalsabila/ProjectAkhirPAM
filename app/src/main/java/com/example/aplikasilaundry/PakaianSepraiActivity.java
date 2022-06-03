package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PakaianSepraiActivity extends AppCompatActivity {

    Button kembali, hitung;
    EditText beratp, berats;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakaian_seprai);

        kembali = (Button) findViewById(R.id.kembalips);
        hitung = (Button) findViewById(R.id.hitungps);
        beratp = (EditText) findViewById(R.id.beratpkg);
        berats = (EditText) findViewById(R.id.beratskg);
        total = (TextView) findViewById(R.id.totalps);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int beratpk = Integer.parseInt(beratp.getText().toString());
                int beratsp = Integer.parseInt(berats.getText().toString());
                if (beratpk==0 && beratsp==0){
                    Toast.makeText(getApplication(), "Masukkan berat pakaian dan seprai", Toast.LENGTH_SHORT).show();
                }else if (beratpk==0){
                    Toast.makeText(getApplication(), "Masukkan berat pakaian", Toast.LENGTH_SHORT).show();
                }else if (beratsp==0){
                    Toast.makeText(getApplication(), "Masukkan berat seprai", Toast.LENGTH_SHORT).show();
                } else if (beratpk==5 && beratsp==3){
                    Toast.makeText(getApplication(), "Anda mendapatkan diskon 10%", Toast.LENGTH_SHORT).show();
                    int totaldis = ((10500*beratpk) + (13000*beratsp)) - (((10500*beratpk) + (13000*beratsp)) * (10/100));
                    String outputdis = String.valueOf(totaldis);
                    total.setText(outputdis.toString());
                } else {
                    int hargattl = (10500*beratpk) + (13000*beratsp);
                    String output = String.valueOf(hargattl);
                    total.setText(output.toString());
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PakaianSepraiActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }
}