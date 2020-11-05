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

public class VocabularyActivity3 extends AppCompatActivity  {
    private TextView question, qCount, timer;
    private Button option1, option2, option3;
    private List<VerbQues> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary3);

        question = findViewById(R.id.Quesv);
        qCount = findViewById(R.id.quesnov);
        timer = findViewById(R.id.timerv);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.summeryesterday);
        question.setTypeface(typeface);

        option1 = findViewById(R.id.opt1v);
        option2 = findViewById(R.id.opt2v);
        option3 = findViewById(R.id.opt3v);

        option1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                countDown.cancel();
                checkAnswer(1, view);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                countDown.cancel();
                checkAnswer(2, view);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                countDown.cancel();
                checkAnswer(3, view);
            }
        });

        getQuestionsList();
        score = 0;
    }

    private void getQuestionsList() {
        questionList = new ArrayList<VerbQues>();

        questionList.add(new VerbQues("She's been ________ for you all day.","A. wait","B. waited","C. waiting", 3));
        questionList.add(new VerbQues("Do you still ________ at the same address? ","A. live","B. living","C. lived",1));
        questionList.add(new VerbQues("Stop ________ and look at me.","A. cry","B. crying","C. cried",2));
        questionList.add(new VerbQues("My sister ________ a word of French.","A. does not speaks","B. does not speak","C. do not speak", 2));
        questionList.add(new VerbQues("Both Alex and Ben ________ to work by bus.","A. go","B. goes","C. went",1));
        questionList.add(new VerbQues("Does she ________ anything about this?","A. knew","B. knows","C. know",3));
        questionList.add(new VerbQues("We ________ here since 2010.","A. will live","B. have lived","C. live",2));
        questionList.add(new VerbQues("I ________ her yesterday afternoon.","A. see","B. saw","C. seen",2));
        questionList.add(new VerbQues("I ________ the work in the next few days.","A. finished","B. had finished","C. will finish",3));
        questionList.add(new VerbQues("Do you want me ________ the car?","A. to wash","B. wash","C. do wash", 1));

        setQuestion();
    }

    private void setQuestion() {
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());

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
                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
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
            playAnim(option3,0,3);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));
            timer.setText(String.valueOf(10));
            startTimer();

        }
        else{
            //Go To Score Activity
            Intent intent = new Intent(VocabularyActivity3.this,Vocabulary_Score.class);
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
                                case 3:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionC());
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