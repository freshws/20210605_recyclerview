package com.example.a20210605_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//ItemView는 HeaderView, ItemView, FooterView로 총 3개가 되었기 때문에 커스텀 ViewHolder는 사용하지 못하고 일반적인 RecyclerView Holder를 사용해야함.
//RecyclerView.ViewHolder 으로 변경함.
public class RecyclerViewPokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_ITEM = 1;


    private List<Pokemon> data;
    private View.OnClickListener onclickLoadMore;
    private View.OnClickListener onclickItem;

    public RecyclerViewPokemonAdapter() {

        this.data = new ArrayList<>();

    }


    //onCreateViewHolder 메소드는 ViewHolder는 만들어주는 메소드
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //언제 TYPE_HEADER, TYPE_ITEM, TYPE_FOOTER 가 적용되는 지는 안드로이드에서 알아서 실행해줌 (신경쓸필요 없음)
        if (viewType == TYPE_HEADER) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon_header, parent, false);

            return new HeaderViewHolder(view);

        } else if (viewType == TYPE_FOOTER) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon_footer, parent, false);

            return new FooterViewHolder(view);

        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

            return new ItemViewHolder(view);

        }

    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof FooterViewHolder) {

            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

        } else {

            //ViewHolder 클래스 이름을 ItemViewHolder라고 변경 했기 때문에...
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            //Header가 생기면서 data의 위치가 한칸씩 밀림
            int pokemonIndex = position - 1;

            //아래의 setText의 매개변수는 String 타입이 들어가야 하므로 만약 toString을 해주지 않으면 타입 불일치로 Exception 발생
            Pokemon pokemon = data.get(pokemonIndex);
            itemViewHolder.textViewId.setText(pokemon.getId() + "번");
            itemViewHolder.textViewId.setTag(pokemonIndex);

            itemViewHolder.textViewName.setText(pokemon.getName());
            itemViewHolder.textViewName.setTag(pokemonIndex);

            itemViewHolder.imageViewPokemon.setTag(pokemonIndex);

            Util.loadImageOn(pokemon.getImgUrl(), itemViewHolder.imageViewPokemon);
        }
    }

    @Override
    public int getItemCount() {

        //Header, Footer도 각각 View를 하나씩 차지한다 그래서 +2 해줘야함.
        return data.size() + 2;
    }

    public void addPokemons(List<Pokemon> pokemons) {

        for (Pokemon pokemon : pokemons) {

            data.add(pokemon);

        }

        /**
         //데이터가 갱신됐다고 자동으로 refresh 되지 않기 때문에 notifyDataSetChanged(); 메소드 사용
         //버튼을 눌렀을 때 데이터를 가져온 것 자체를 자동으로 해주지 않음
         notifyDataSetChanged();
         **/

        //notifyDataSetChanged();와 다르게 아이템이 추가 되었고 어디에 추가 되어는지까지 정해주는 것
        //1번 위치에 20개의 아이템이 들어간다는 것 (Header가 1번 위치...)(getLoadCount() 메소스 사용전 20 이었음)
        //getLoadCount() 의 리턴 값은 정수형이고 몇개의 아이템이 들어가지 결정 된다.

        int headerCount = 1;
        notifyItemRangeInserted(headerCount + getLoadCount(), getLoadCount());

    }

    public void setOnclickItem(View.OnClickListener onclickItem) {

        this.onclickItem = onclickItem;

    }

    public void setOnclickLoadMore(View.OnClickListener onclickLoadMore) {

        this.onclickLoadMore = onclickLoadMore;

    }

    public Pokemon getPokemon(int index) {

        return data.get(index);
    }


    public int getLoadCount() {
        return 10;
    }

    //현재 코드에서 data의 사이즈는 계속 바뀐다.
    //가져온 데이터의 양이 늘어 날 수도록 데이터의 사이즈도 바뀌는 것
    public int getDataSize() {
        return data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId;
        public TextView textViewName;
        public ImageView imageViewPokemon;

        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.item_pokemon__textViewId);
            textViewName = itemView.findViewById(R.id.item_pokemon__textViewName);
            imageViewPokemon = itemView.findViewById(R.id.item_pokemon__imageViewPokemon);

            textViewId.setOnClickListener(onclickItem);
            textViewName.setOnClickListener(onclickItem);
            imageViewPokemon.setOnClickListener(onclickItem);

        }
    }

    //HeaderViewHolder, FooterViewHolder 각각 만들어 준다.
    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull @NotNull View View) {
            super(View);

        }
    }

    //HeaderViewHolder, FooterViewHolder 각각 만들어 준다.
    class FooterViewHolder extends RecyclerView.ViewHolder {
        public Button buttonLoadMore;

        public FooterViewHolder(@NonNull @NotNull View View) {
            super(View);

            buttonLoadMore = itemView.findViewById(R.id.item_pokemon__footer__buttonLoadMore);
            buttonLoadMore.setOnClickListener(onclickLoadMore);

        }
    }


    //getItemViewType는 position이 몇번 데이터인지 알려주는 메소드
    @Override
    public int getItemViewType(int position) {

        //data의 갯수가 20가 있다 했을 때, data의 범위는 0 ~ 19
        //그런데 Header가 생기면서 데이터의 범위는 1 ~ 20으로 변경됨

        if (position == 0) {

            return TYPE_HEADER;

        } else if (position == data.size() + 1) {

            return TYPE_FOOTER;

        } else {

            return TYPE_ITEM;

        }

    }

}
