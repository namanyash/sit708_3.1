package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView testScoreView;
    TextView congoTextView;
    Button retakeButton;
    Button finishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent valueIntent = getIntent();
        int totalCorrect = 0;
        for(int i = 1 ; i <= 5 ; i ++) {
            if (valueIntent.getBooleanExtra(Integer.toString(i), false)) {
                totalCorrect++;
            }
        }
        testScoreView = findViewById(R.id.testScoreView);
        congoTextView = findViewById(R.id.congoTextView);
        congoTextView.setText("Congratulations "+ valueIntent.getStringExtra("name"));
        testScoreView.setText("Score: "+ Integer.toString(totalCorrect)+"/5");
        retakeButton = findViewById(R.id.retakeButton);
        retakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent newIntent = new Intent(ResultActivity.this, QuizActivity.class);
                newIntent.putExtra("name",valueIntent.getStringExtra("name"));
                newIntent.putExtra("itr",1);
                startActivity(newIntent);
            }
        });
        finishButton = findViewById(R.id.Finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }
}