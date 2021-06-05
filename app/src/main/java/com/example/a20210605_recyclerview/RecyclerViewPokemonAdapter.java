package com.example.a20210605_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPokemonAdapter extends RecyclerView.Adapter<RecyclerViewPokemonAdapter.ViewHolder> {

    private List<Integer> data;

    public RecyclerViewPokemonAdapter(List<Integer> data) {

        this.data = data;

    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewPokemonAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new RecyclerViewPokemonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewPokemonAdapter.ViewHolder holder, int position) {

        //아래의 setText의 매개변수는 String 타입이 들어가야 하므로 만약 toString을 해주지 않으면 타입 불일치로 Exception 발생
        holder.textViewId.setText(data.get(position).toString() + "번");
        holder.textViewId.setTag(position);


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.item_pokemon__textViewId);

        }
    }

}
