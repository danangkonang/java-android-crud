package com.example.ceodeaja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceodeaja.R;
import com.example.ceodeaja.model.DataModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listAnimals;

    public AdapterData(Context ctx, List<DataModel> listAnimals) {
        this.ctx = ctx;
        this.listAnimals = listAnimals;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_animal, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listAnimals.get(position);

        //  holder.tvId.setText(String.valueOf(dm.getAnimal_id())); //jika data integer
        holder.tvId.setText(dm.getAnimal_id());
        holder.tvName.setText(dm.getName());
        holder.tvColor.setText(dm.getColor());
        holder.tvDescription.setText(dm.getDescription());
    }

    @Override
    public int getItemCount() {
        return listAnimals.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvColor, tvDescription;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvColor = itemView.findViewById(R.id.tv_color);
            tvDescription = itemView.findViewById(R.id.tv_descrip);
        }
    }
}
