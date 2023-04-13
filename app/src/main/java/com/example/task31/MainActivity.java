package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    EditText enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=findViewById(R.id.startButton);
        enteredName=findViewById(R.id.userNameInput);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String value = enteredName.getText().toString();
                if(value == null || value.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter a value for the name input",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent newIntent = new Intent(MainActivity.this, QuizActivity.class);
                newIntent.putExtra("name",value);
                newIntent.putExtra("itr",1);
                startActivity(newIntent);
            }
        });
    }

}