package com.example.aplikasilaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PakaianActivity extends AppCompatActivity {

    Button hitung, kembali;
    EditText berat;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakaian);

        hitung = (Button) findViewById(R.id.hitungp);
        kembali = (Button) findViewById(R.id.kembalip);
        berat = (EditText)findViewById(R.id.beratkg);
        total = (TextView) findViewById(R.id.total);

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
                return 10500*berat;
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PakaianActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }

}