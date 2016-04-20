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
    private String[] vowels = new String[] {"A", "E", "I", "O", "U"};
    private ArrayList<String> boggleWords = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        Random randomGenerator = new Random();
        while (boggleWords.size() < 6) {
            int randomInt = randomGenerator.nextInt(26);
            String letter = letters[randomInt];
            boggleWords.add(letter);
        }

        int vowelCount = 0;
        for (int i=0; i < boggleWords.size(); i++) {
            for (int j=0; j < vowels.length; j++) {
                if (boggleWords.get(i) == vowels[j]) {
                    vowelCount +=1;
                }
            }
        }

        if (vowelCount >=2) {
            for (int i=0; i < 2; i++) {
                int randomInt = randomGenerator.nextInt(26);
                String letter = letters[randomInt];
                boggleWords.add(letter);
            }
        }

        if (vowelCount == 1) {
            int randomInt = randomGenerator.nextInt(26);
            String letter = letters[randomInt];
            boggleWords.add(letter);

            int randomVowelInt = randomGenerator.nextInt(5);
            String vowel = vowels[randomVowelInt];
            boggleWords.add(vowel);
        }

        if (vowelCount == 0) {
            for (int i=0; i < 5; i++) {
                int randomInt = randomGenerator.nextInt(5);
                String vowel = vowels[randomInt];
                boggleWords.add(vowel);
            }
        }

        ArrayAdapter wordAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, boggleWords);
        mLettersListView.setAdapter(wordAdapter);
    }
}
