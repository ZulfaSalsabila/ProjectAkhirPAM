package com.example.aplikasilaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasilaundry.adapter.PakaianAdapter;
import com.example.aplikasilaundry.model.Pakaian;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewPakaian extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Pakaian> list = new ArrayList<>();
    private PakaianAdapter pakaianAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pakaian);

        recyclerView = findViewById(R.id.recylar);

        progressDialog = new ProgressDialog(ViewPakaian.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data...");
        pakaianAdapter = new PakaianAdapter(getApplicationContext(), list);

        pakaianAdapter.setDialog(new PakaianAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] diaglogItem = {"EditData", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewPakaian.this);

                dialog.setItems(diaglogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), PakaianActivity.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("total", list.get(pos).getBerat());
                                intent.putExtra("berat", list.get(pos).getTotal());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(pakaianAdapter);

    }

    private void deleteData(String id) {
        progressDialog.show();

        db.collection("pakaian").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(ViewPakaian.this, "Data Gagal di hapus", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ViewPakaian.this, "Data Berhasil di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();

        db.collection("pakaian")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        if(task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()){
                                Pakaian pakaian  = new Pakaian(document.getString("total"), document.getString("berat"));
                                pakaian.setId(document.getId());
                                list.add(pakaian);
                            }
                            pakaianAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(ViewPakaian.this, "Data Gagal Di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}