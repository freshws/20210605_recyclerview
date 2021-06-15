package com.example.a20210605_recyclerview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a20210605_recyclerview.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("id", 0);

        setTitle(id + "번 포켓몬 상세정보");

        //activity 화면에 XML 말고 자바 코딩으로 글자가 나오도록 하는 것
        TextView textView = findViewById(R.id.activity_detail__textViewId);
        textView.setText(id + " 번");

    }
}