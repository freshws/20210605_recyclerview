package com.example.a20210605_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//"<RecyclerViewPokemonAdapter.ViewHolder>"는 사용하려는 ViewHolder 기입
public class RecyclerViewPokemonAdapter extends RecyclerView.Adapter<RecyclerViewPokemonAdapter.ViewHolder> {

    private List<Pokemon> data;

    public RecyclerViewPokemonAdapter(List<Pokemon> data) {

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

        Pokemon pokemon = data.get(position);

        //아래의 setText의 매개변수는 String 타입이 들어가야 하므로 만약 toString을 해주지 않으면 타입 불일치로 Exception 발생
        holder.textViewId.setText(pokemon.getId() + "번");
        holder.textViewName.setText(pokemon.getName());
        holder.textViewId.setTag(position);

        Util.loadImageOn(pokemon.getImgUrl(), holder.imageViewPokemon);

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId;
        public TextView textViewName;
        public ImageView imageViewPokemon;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.item_pokemon__textViewId);
            textViewName = itemView.findViewById(R.id.item_pokemon__textViewName);
            imageViewPokemon = itemView.findViewById(R.id.item_pokemon__imageViewPokemon);

        }
    }

}
