package com.example.aplikasilaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class PakaianActivity extends AppCompatActivity {

    Button hitung, kembali, simpan;
    EditText berat;
    TextView total;
    String isiberat;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    String data,output;
    int harga, beratPakaian, hargaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakaian);

        hitung = (Button) findViewById(R.id.hitungp);
        kembali = (Button) findViewById(R.id.kembalip);
        berat = (EditText)findViewById(R.id.beratkg);
        total = (TextView) findViewById(R.id.total);
        simpan = findViewById(R.id.simpanData);

        progressDialog = new ProgressDialog(PakaianActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan....");

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (berat.length()==0){
                    Toast.makeText(getApplication(), "Masukkan berat", Toast.LENGTH_SHORT).show();
                } else {
                    isiberat = berat.getText().toString();

                    // Perhitungan
                    beratPakaian = Integer.parseInt(isiberat);
                    hargaTotal = (beratPakaian * 10500);

                    // Convert to String
                    output = String.valueOf(hargaTotal); // Total
                    data = String.valueOf(beratPakaian); // Berat Pakaian

                    total.setText(output.toString());



//                    int berat = Integer.parseInt(isiberat);
//                    int hb = hargatotal(berat);
//                    output = String.valueOf(hb);
//                    data = String.valueOf(berat);
//                    total.setText(output.toString());
                }
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

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(output, data);
            }
        });

        // Edit Database
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("id");
            berat.setText(intent.getStringExtra("berat"));
            total.setText(intent.getStringExtra("total"));
        }

    }

    private void saveData(String output, String isiberat) {
        Map<String,Object> pakaian = new HashMap<>();

        pakaian.put("total", output);
        pakaian.put("berat", isiberat);

        progressDialog.show();

        if (id!=null){
            db.collection("pakaian").document(id)
                    .set(pakaian)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PakaianActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(PakaianActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            db.collection("pakaian")
                    .add(pakaian)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(PakaianActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ViewPakaian.class));
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PakaianActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }

}