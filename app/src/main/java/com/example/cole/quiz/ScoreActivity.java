package com.example.cole.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreText = (TextView) findViewById(R.id.textView_score);

        Intent i = getIntent();
        int score = i.getIntExtra(MainActivity.EXTRA_SCORE, 0);
        int numQuestions = i.getIntExtra(MainActivity.EXTRA_NUM_QUESTIONS, 0);
        scoreText.setText("Score: " + score + " / " + numQuestions);

    }
}
