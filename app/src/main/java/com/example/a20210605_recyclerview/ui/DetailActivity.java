package com.example.a20210605_recyclerview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20210605_recyclerview.Pokemon;
import com.example.a20210605_recyclerview.R;
import com.example.a20210605_recyclerview.databinding.ActivityDetailBinding;
import com.example.a20210605_recyclerview.service.PokemonService;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //데이터 바인딩의 효과로 activity_detail.xml에 해당하는 객체를 아래처럼 한번에 만들어준다.
        //binding 이란 변수에는 activity_detail.xml의 모든 내용이 전부 들어가 있다.
        //데이터 바인딩으로 setContentView(R.layout.activity_detail); 은 없어도됨, 대신해줌
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int id = getIntent().getIntExtra("id", 0);

        setTitle(id + "번 포켓몬 상세정보");

        //데이터 바인딩을 했기 때문에 findViewById(R.id.activity_detail__textViewId);로 연결하지 않다도됨.\
        //xml내부에 여러 id가 있었다면 복잡해졌을 코드를 간단하게 해줌
        //activityDetailTextViewId은 xml 파일의 id 이다.
        binding.activityDetailTextViewId.setText(id + " 번");


        /**
        //activity 화면에 XML 말고 자바 코딩으로 글자가 나오도록 하는 것
        TextView textView = findViewById(R.id.activity_detail__textViewId);
        textView.setText(id + " 번");
         **/

        //MainActivity에서 아이템(recyclerViewPokemonAdapter.setOnclickItem)을 클릭하면 DetailActivity로 전환됨
        //그렇기 때문에 아이템을 클릭했을 때 토스트 메세지가 뜨게 하려면 DetailActivity에서 호출해야됨
        final PokemonService pokemonService = new PokemonService();

        pokemonService.getPokemon(id,responseBody -> {

            Pokemon pokemon = responseBody.getPokemon();

            binding.activityDetailTextViewName.setText(pokemon.getName());

            //Toast.makeText(this,responseBody.getName(),Toast.LENGTH_SHORT).show();

        } );

    }
}