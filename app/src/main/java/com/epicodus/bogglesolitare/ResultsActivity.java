package com.epicodus.bogglesolitare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    @Bind(R.id.wordsListView)
    ListView mWordsListView;
    @Bind(R.id.playAgainButton)
    Button mPlayAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<String> savedWords = intent.getStringArrayListExtra("savedWords");
        ArrayAdapter wordAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, savedWords);
        mWordsListView.setAdapter(wordAdapter);

        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ResultsActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
