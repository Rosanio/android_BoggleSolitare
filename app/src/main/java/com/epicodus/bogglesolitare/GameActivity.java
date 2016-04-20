package com.epicodus.bogglesolitare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    private TextView mNewWordTextView;
    @Bind(R.id.lettersListView)
    ListView mLettersListView;
    private String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ArrayList<String> boggleWords = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        Random randomGenerator = new Random();
        while (boggleWords.size() < 8) {
            int randomInt = randomGenerator.nextInt(26);
            String letter = letters[randomInt];
            boggleWords.add(letter);
        }

        ArrayAdapter wordAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, boggleWords);
        mLettersListView.setAdapter(wordAdapter);
    }
}
