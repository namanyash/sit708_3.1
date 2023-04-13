package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuizActivity extends AppCompatActivity {
    String name;
    TextView qNumberView;
    TextView questionView;
    ProgressBar progressBar;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button submitAns;

    static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    Button selectedButton;
    Button correctAnswer =null;
    Boolean resultShown = false;
    public void resetOptions(){
        selectedButton = null;
        option1.setBackgroundColor(Color.rgb(220,200,200));
        option1.setTextColor(Color.BLACK);
        option2.setBackgroundColor(Color.rgb(220,200,200));
        option2.setTextColor(Color.BLACK);
        option3.setBackgroundColor(Color.rgb(220,200,200));
        option3.setTextColor(Color.BLACK);
        option4.setBackgroundColor(Color.rgb(220,200,200));
        option4.setTextColor(Color.BLACK);
    }
    public void setCorrectAnswer(int i){
        switch(i) {
            case 0:
               correctAnswer=option1;
                break;
            case 1:
                correctAnswer=option2;
                break;
            case 2:
                correctAnswer=option3;
                break;
            case 3:
                correctAnswer=option4;
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int submittedAnswer = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        qNumberView=findViewById(R.id.qNumberView);
        questionView=findViewById(R.id.questionView);
        progressBar=findViewById(R.id.progressBar);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        submitAns=findViewById(R.id.submitAns);
        Intent valueIntent = getIntent();
        name = valueIntent.getStringExtra("name");
        int itr = valueIntent.getIntExtra("itr",0);
        qNumberView.setText("Q."+Integer.toString(itr));
        progressBar.setProgress((int) (itr*progressBar.getMax()*0.2));
        String value = "q"+Integer.toString(itr);
        int pageQuestionIdentifier = this.getResources().getIdentifier(value, "string", this.getPackageName());
        String pageQuestion = getString(pageQuestionIdentifier);
        int pageOptionIdentifier = this.getResources().getIdentifier(value, "array", this.getPackageName());
        String[] options = getResources().getStringArray(pageOptionIdentifier);
        questionView.setText(pageQuestion);
        List<Integer> order = Arrays.asList(1,2,3,0);
        Collections.shuffle(order);
        option1.setText(options[order.get(0)]);
        option2.setText(options[order.get(1)]);
        option3.setText(options[order.get(2)]);
        option4.setText(options[order.get(3)]);
        int indexOfCorrectAnswer= order.indexOf(0);
        setCorrectAnswer(indexOfCorrectAnswer);
        submitAns.setText("Submit");
        resetOptions();
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(resultShown){
                    return;
                }
                resetOptions();
                selectedButton = option1;
                option1.setBackgroundColor(Color.BLACK);
                option1.setTextColor(Color.rgb(220,200,200));
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(resultShown){
                    return;
                }
                resetOptions();
                selectedButton = option2;
                option2.setBackgroundColor(Color.BLACK);
                option2.setTextColor(Color.rgb(220,200,200));
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(resultShown){
                    return;
                }
                resetOptions();
                selectedButton = option3;
                option3.setBackgroundColor(Color.BLACK);
                option3.setTextColor(Color.rgb(220,200,200));
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(resultShown){
                    return;
                }
                resetOptions();
                selectedButton = option4;
                option4.setBackgroundColor(Color.BLACK);
                option4.setTextColor(Color.rgb(220,200,200));
            }
        });
        submitAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(resultShown){
                    if(itr == 5){
                        Bundle bundle = valueIntent.getExtras();
                        if (bundle != null) {
                            for (String key : bundle.keySet()) {
                                Log.e("TAG", key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
                            }
                        }
                        Intent newIntent = new Intent(QuizActivity.this, ResultActivity.class);
                        newIntent.putExtra("name",name);
                        for(int i = 1 ; i <= 4 ; i ++){
                            newIntent.putExtra(Integer.toString(i),valueIntent.getBooleanExtra(Integer.toString(i), false));
                        }
                        if(selectedButton==correctAnswer) {
                            newIntent.putExtra(Integer.toString(5), true);
                        }
                        else{
                            newIntent.putExtra(Integer.toString(5), false);
                        }
                        newIntent.putExtra("ans1",valueIntent.getBooleanExtra("1",false));
                        startActivity(newIntent);
                    }
                    else{
                        valueIntent.putExtra("itr", itr+1);
                        if(selectedButton==correctAnswer) {
                            valueIntent.putExtra(Integer.toString(itr), true);
                        }
                        else{
                            valueIntent.putExtra(Integer.toString(itr), false);
                        }
                        startActivity(valueIntent);
                    }
                    return;
                }
                if(selectedButton == null){
                    Toast.makeText(QuizActivity.this, "Please select an option",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String Answer = selectedButton.getText().toString();
                resultShown = true;
                submitAns.setText("Next");
                if(Answer == options[0]){
                    selectedButton.setBackgroundColor(Color.GREEN);
                    selectedButton.setTextColor(Color.BLACK);
                }
                else{
                    selectedButton.setBackgroundColor(Color.RED);
                    selectedButton.setTextColor(Color.rgb(220,200,200));
                    correctAnswer.setBackgroundColor(Color.GREEN);
                    correctAnswer.setTextColor(Color.BLACK);
                }

            }
        });


    }
}