package com.example.cole.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton, falseButton, nextButton;
    private TextView questionText;
    private List<Question> questionBank;
    private int questionID, score;

    public static final String EXTRA_SCORE = "score";
    public static final String EXTRA_NUM_QUESTIONS = "numQuestions";

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        initQuestionBank();

        //load the first question
        //check if we're resuming from a previous state
        if(savedInstanceState != null) {
            questionID = savedInstanceState.getInt("question we're on", 0);
            score = savedInstanceState.getInt("current score");
            if(questionID < questionBank.size()) {
                questionText.setText(questionBank.get(questionID).getQuestionText());
            }
            else {
                questionText.setText(questionBank.get(questionBank.size()-1).getQuestionText());
            }
            switchEnabledButtons(savedInstanceState.getBoolean("next button enabled"));
        }
        else {
            questionID = -1;
            score = 0;
            setUpNextQuestion();
        }
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
        questionBank.add(new Question(getString(R.string.apple_raven_question), true));
        questionBank.add(new Question(getString(R.string.question_question), false));
        questionBank.add(new Question(getString(R.string.ship_question), false));
        questionBank.add(new Question(getString(R.string.tolerance_question), true));
        questionBank.add(new Question(getString(R.string.heap_question), true));
        questionBank.add(new Question(getString(R.string.birthday_question), true));
        questionBank.add(new Question(getString(R.string.give_question), true));
        questionBank.add(new Question(getString(R.string.bliss_question), false));
        questionBank.add(new Question(getString(R.string.button_question), false));
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
                checkAnswer(false);
                switchEnabledButtons(true); //sets nextButton to enabled and disables true and false
                break;
            case R.id.button_true:
                checkAnswer(true);
                switchEnabledButtons(true);
                break;
            case R.id.button_next:
                if(questionID+1 < questionBank.size()) {
                    setUpNextQuestion();
                }
                else if (questionID+1 == questionBank.size())
                {
                    questionID++;
                    openScoreActivity();
                }
                else
                {
                    questionID = -1;
                    score = 0;
                    setUpNextQuestion();
                }
                break;
        }
    }

    private void openScoreActivity() {
        Intent i = new Intent(MainActivity.this, ScoreActivity.class);
        i.putExtra(EXTRA_SCORE, score);
        i.putExtra(EXTRA_NUM_QUESTIONS, questionBank.size());
        startActivity(i);
    }

    /**
     * @param next true if nextButton is to be enabled and false if nextButton is to be disabled
     *             when nextButton is enabled trueButton and falseButton are disabled and vice versa
     */
    private void switchEnabledButtons(boolean next) {
        falseButton.setEnabled(!next);
        trueButton.setEnabled(!next);
        nextButton.setEnabled(next);
    }

    /**
     * @param ans toasts sent out depending on whether or not the user answer matches the correct answer
     */
    public void checkAnswer(boolean ans){
        if(questionBank.get(questionID).checkAnswer(ans)){
            Toast.makeText(this, "You are profoundly wise", Toast.LENGTH_SHORT).show();
            score++;
        }
        else{
            Toast.makeText(this, "What?... No.", Toast.LENGTH_SHORT).show();
        }

    }

    private void setUpNextQuestion() {
        questionID++;
        questionText.setText(questionBank.get(questionID).getQuestionText());
        switchEnabledButtons(false);
    }

    //To prevent resetting during rotation, we use SaveInstanceState
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: method fired");
        //store the current question number
        outState.putInt("question we're on", questionID);
        //store the current score
        outState.putInt("current score", score);
        //store the current enabled buttons
        outState.putBoolean("next button enabled", nextButton.isEnabled());
    }

    //Android Lifecycle Methods
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: method fired");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: method fired");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: method fired");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: method fired");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: method fired");
    }
}
