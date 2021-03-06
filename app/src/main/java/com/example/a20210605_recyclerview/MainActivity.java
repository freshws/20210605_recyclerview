package com.example.a20210605_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210605_recyclerview.databinding.ActivityMainBinding;
import com.example.a20210605_recyclerview.service.PokemonService;
import com.example.a20210605_recyclerview.ui.BaseActivity;
import com.example.a20210605_recyclerview.ui.DetailActivity;

public class MainActivity extends BaseActivity {

    private PokemonService pokemonService;
    private RecyclerViewPokemonAdapter recyclerViewPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity에도 databinding 적용
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("포켓몬 리스트");

        pokemonService = new PokemonService();

        //데이터와 어뎁터가 연결되지 않은, 빈껍데이 리싸이클러 뷰 어댑터 생성
        recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();

        //binding 사용으로 findById 메소드는 사용하지 않아도됨
        binding.activityMainRecyclerViewPokemon.setAdapter(recyclerViewPokemonAdapter);

        //아이템을 클릭 했을 때 화면 전환이 발생하도록 함
        recyclerViewPokemonAdapter.setOnclickItem(v -> {

            int pokemonIndex = (int) v.getTag();

            //CurrentActivity를 가져오는 구문에 따라 더이상 "this"를 사용하지 않아도됨
            //ViewModel를 사용할 때 this를 사용하는 것보다 유용하다고 함...(왜 그런지는 찾아봐야함...)
            Intent intent = new Intent(Util.getCurrentActivity(), DetailActivity.class);
            Pokemon pokemon = recyclerViewPokemonAdapter.getPokemon(pokemonIndex);
            intent.putExtra("id", pokemon.getId());
            startActivity(intent);

        });


        //"더 보기" 버튼이 클릭 되었을 때 통신이 발생하고 리스트가 뿌려지도록 변경
        //아래 람다식의 v는 버튼이다.
        recyclerViewPokemonAdapter.setOnclickLoadMore(v -> {

            //더 보기 버튼을 더블클릭 했을 때 데이터가 로딩되기 전에 메소드 호출되는 것을 방지하기 위해 생성
            //버튼이 눌리지 않도록 하는 것
            v.setEnabled(false);

            //"더 보기" 버튼을 클릭 했을 때 아이템 리스트가 나옴
            loadMore(v);




        });

        //"더 보기" 버튼을 클릭하지 않아도 기본적으로 아이템 리스트가 디폴트로 떠 있게 해줌
        loadMore(null);

    }

    public void loadMore(View view) {
        //더 보기 버튼을 누를 때 마다 리스트를 계속해서 가져오는 코드
        //처음에는 가져온 데이터가 없으니 recyclerViewPokemonAdapter.getDataSize() 가 0 이지만 두번째 더 보기를 누룬 후엔
        //recyclerViewPokemonAdapter.getDataSize() 에 의해서 가져온 것 만큼 뒤의 데이터가 와야 한다.
        //결극 recyclerViewPokemonAdapter.getDataSize(), recyclerViewPokemonAdapter.getDataSize() 메소드는 getPokemons 메소드의 매개변수 이다.
        pokemonService.getPokemons(recyclerViewPokemonAdapter.getDataSize(), recyclerViewPokemonAdapter.getLoadCount(), responseBody -> {

            recyclerViewPokemonAdapter.addPokemons(responseBody.getResults());
            //버튼이 눌려지도록 활성화
            //v.setEnabled(true); 메소드는 getPokemons 안에 있어야함
            //데이터가 전부 로딩되야 즉, 비동기 데이터 처리가 완료되면 버튼이 활성화됨.

            if (view != null) {

                view.setEnabled(true);
                //v.setEnabled(true); 메소드가 여기에 있으면 아무의미 없음
                //비동기 통신이기 때문에 위의 getPokemons가 호출되고 데이터가 오기 전에 v.setEnabled(true);가 호출된다.
                //즉 더블클릭 하면 v.setEnabled();가 없을 때 처럼 데이터가 오기 전에 v.setEnabled(true);가 되어 더블클릭으로 인한 마구잡이 로딩이 됨

            }
        });

    }
}