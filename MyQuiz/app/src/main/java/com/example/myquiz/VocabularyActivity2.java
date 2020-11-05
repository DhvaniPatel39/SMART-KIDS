package com.example.myquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView question, qCount, timer;
    private Button option1, option2;
    private List<SpellingQues> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary2);

        question = findViewById(R.id.SpelQues);
        qCount = findViewById(R.id.quesno);
        timer = findViewById(R.id.timer);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.summeryesterday);
        question.setTypeface(typeface);

        option1 = findViewById(R.id.opt1);
        option2 = findViewById(R.id.opt2);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);

        getQuestionsList();
        score = 0;
    }

    private void getQuestionsList() {
        questionList = new ArrayList<SpellingQues>();

        questionList.add(new SpellingQues("Do you think you can get the perfect score?","Definately","Definitely",2));
        questionList.add(new SpellingQues("Another word for perk is...","Privilege","Privelige",1));
        questionList.add(new SpellingQues("If you're certain you're right, you're...","Adament","Adamant",2));
        questionList.add(new SpellingQues("To pressure and intimidate is to...","Harass","Harrass",1));
        questionList.add(new SpellingQues("If something's really tiny, it's...","Minuscule","Miniscule",1));
        questionList.add(new SpellingQues("You should not undress...","Publically","Publicly",2));
        questionList.add(new SpellingQues("You know, the day after the 11th is...","Twelfth","Twelvth",1));
        questionList.add(new SpellingQues("Two lines are...","Parralel","Parallel",2));
        questionList.add(new SpellingQues("If someone's hot-headed, you might call them...","Fiery","Firey",1));
        questionList.add(new SpellingQues("Soldiers wear...","Camoflage","Camouflage",2));

        setQuestion();
    }

    private void setQuestion() {
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());

        qCount.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));

        startTimer();
        quesNum =0;

    }

    private void startTimer() {
        countDown = new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long l) {
                if(l < 10000)
                    timer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();

            }
        };
        countDown.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int selectedOption = 0;

        switch (view.getId()){
            case R.id.option1:
                selectedOption = 1;
                break;
            case R.id.option2:
                selectedOption = 2;
                break;

            default:
        }
        countDown.cancel();
        checkAnswer(selectedOption, view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectedOption, View view) {
        if(selectedOption == questionList.get(quesNum).getCorrectAns()){
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else{
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (questionList.get(quesNum).getCorrectAns()){
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        },2000);
    }

    private void changeQuestion() {
        if(quesNum < questionList.size()-1){
            quesNum++;
            playAnim(question,0, 0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));
            timer.setText(String.valueOf(10));
            startTimer();

        }
        else{
            //Go To Score Activity
            Intent intent = new Intent(VocabularyActivity2.this,ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" +String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //QuestionActivity.this.finish();
        }
    }

    private void playAnim(final View view, final int value, final int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).
                setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(value == 0){
                            switch(viewNum){
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionB());
                                    break;
                            }

                            if(viewNum != 0){
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99C03")));
                            }

                            playAnim(view,1,viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}