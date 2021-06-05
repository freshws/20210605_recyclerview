package com.example.a20210605_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewPokemonAdapter extends RecyclerView.Adapter<RecyclerViewPokemonAdapter.ViewHolder> {

    private List<Integer> data;

    public RecyclerViewPokemonAdapter(List<Integer> data) {

        this.data = data;

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewHolder holder, int position) {

        //setText는 String 값을 매개변수로 받기 때문에 "data.get(position)"에다가 " + "번"" 해주면 전체적으로 String 값이 된다.
        holder.textViewId.setText(data.get(position) + "번");
        holder.textViewId.setText(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.item_pokemon__textViewId);
        }
    }


}
