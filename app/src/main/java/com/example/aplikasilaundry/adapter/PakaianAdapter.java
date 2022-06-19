package com.example.aplikasilaundry.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasilaundry.R;
import com.example.aplikasilaundry.model.Pakaian;

import java.util.List;

public class PakaianAdapter extends RecyclerView.Adapter<PakaianAdapter.PakaianViewHolder> {

    private Context context;
    private List<Pakaian> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public PakaianAdapter(Context context, List<Pakaian> list){
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public PakaianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent, false);
        return new PakaianViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PakaianViewHolder holder, int position) {

        holder.nama.setTextSize(25);
        holder.itemBerat.setTextSize(20);
        holder.totHarga.setTextSize(28);

        holder.nama.setTextColor(Color.BLACK);
        holder.itemBerat.setTextColor(Color.BLACK);
        holder.totHarga.setTextColor(Color.BLACK);

        holder.nama.setText("Pakaian");
        holder.itemBerat.setText("Berat : " + list.get(position).getTotal() + " Kg");
        holder.totHarga.setText("Total Harga : "+ "Rp " +list.get(position).getBerat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PakaianViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView nama,totHarga,itemBerat;

        public PakaianViewHolder(@NonNull View itemView){
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            nama = itemView.findViewById(R.id.nmPakaian);
            itemBerat = itemView.findViewById(R.id.beratBarang);
            totHarga = itemView.findViewById(R.id.totHarga);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
