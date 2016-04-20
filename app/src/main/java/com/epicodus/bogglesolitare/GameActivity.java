package com.epicodus.bogglesolitare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;



public class GameActivity extends AppCompatActivity {
    public static final String TAG = GameActivity.class.getSimpleName();
    String newWord = "";
    @Bind(R.id.newWordTextView)
    TextView mNewWordTextView;
    @Bind(R.id.lettersListView)
    ListView mLettersListView;
    @Bind(R.id.saveWordButton)
    Button mSaveWordButton;
    @Bind(R.id.resultsButton)
    Button mResultsButton;
    @Bind(R.id.shuffleButton) Button mShuffleButton;
    private String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] vowels = new String[] {"A", "E", "I", "O", "U"};
    private ArrayList<String> boggleWords = new ArrayList<String>();
    private ArrayList<String> savedWords = new ArrayList<String>();

    public Boolean duplicateCheck(ArrayList arrayList, String letter) {
        if (arrayList.contains(letter)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> addLetters(String[] lettersArray, ArrayList<String> wordArrayList, Integer desiredArraySize) {
        Random randomGenerator = new Random();
        while(wordArrayList.size() < desiredArraySize) {
            int randomInt = randomGenerator.nextInt(lettersArray.length);
            String letter = lettersArray[randomInt];
            if(duplicateCheck(wordArrayList, letter)) {
                continue;
            } else {
                wordArrayList.add(letter);
            }
        }
        return wordArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        boggleWords = addLetters(letters, boggleWords, 6);


        int vowelCount = 0;
        for (int i=0; i < boggleWords.size(); i++) {
            for (int j=0; j < vowels.length; j++) {
                if (boggleWords.get(i) == vowels[j]) {
                    vowelCount +=1;
                }
            }
        }

        if (vowelCount >=2) {
            boggleWords = addLetters(letters, boggleWords, 8);

        }

        if (vowelCount == 1) {
            boggleWords = addLetters(letters, boggleWords, 7);

            boggleWords = addLetters(vowels, boggleWords, 8);

        }

        if (vowelCount == 0) {
            boggleWords = addLetters(vowels, boggleWords, 8);
        }

        final ArrayAdapter wordAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, boggleWords);
        mLettersListView.setAdapter(wordAdapter);

        mLettersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String wordInput = ((TextView)view).getText().toString();
                newWord += wordInput;
                mNewWordTextView.setText(newWord);
            }
        });

        mSaveWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newWord.length() >= 3) {
                    Toast.makeText(GameActivity.this, newWord + " has been saved", Toast.LENGTH_LONG).show();
                    savedWords.add(newWord);
                    newWord = "";
                    mNewWordTextView.setText(newWord);
                    Log.d(TAG, "" + savedWords);
                } else {
                    Toast.makeText(GameActivity.this, newWord + " is too short!", Toast.LENGTH_LONG).show();
                    newWord = "";
                    mNewWordTextView.setText(newWord);
                }
            }
        });

        mShuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(boggleWords);
                wordAdapter.notifyDataSetChanged();
            }
        });

        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (GameActivity.this, ResultsActivity.class);
                intent.putExtra("savedWords", savedWords);
                startActivity(intent);
            }
        });
    }
}
