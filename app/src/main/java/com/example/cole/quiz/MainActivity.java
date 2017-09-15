package com.example.cole.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton, falseButton, nextButton;
    private TextView questionText;

    private List<Question> questionBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        initQuestionBank();
        //load the first question
        questionText.setText(questionBank.get(0).getQuestionText());
    }

    private void initQuestionBank() {
        questionBank = new ArrayList<>();
        questionBank.add(new Question(getString(R.string.old_question), true));
        questionBank.add(new Question(getString(R.string.paradox_question), false));
        questionBank.add(new Question(getString(R.string.wood_chuck_question), true));
        questionBank.add(new Question(getString(R.string.sky_question), false));
        questionBank.add(new Question(getString(R.string.common_question), false));
        questionBank.add(new Question(getString(R.string.sound_question), false));
        questionBank.add(new Question(getString(R.string.window_question), true));
    }

    private void setListeners() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);
        questionText = (TextView) findViewById(R.id.text_question);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_false:
                break;
            case R.id.button_true:
                break;
            case R.id.button_next:
                break;
        }
    }
}
