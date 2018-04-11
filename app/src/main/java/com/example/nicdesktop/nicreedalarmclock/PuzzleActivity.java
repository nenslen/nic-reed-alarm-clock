package com.example.nicdesktop.nicreedalarmclock;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class PuzzleActivity extends AppCompatActivity {

    // Declare inputs
    TextView textNum1;
    TextView textNum2;
    TextView textOperator;
    EditText textAnswer;
    TextView textNumPuzzles;

    // Declare alarm variables
    Uri notification;
    Ringtone r;
    int numPuzzles;
    int solvedPuzzles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        // Define elements
        textNum1 = (TextView)findViewById(R.id.txtNum1);
        textNum2 = (TextView)findViewById(R.id.txtNum2);
        textOperator = (TextView)findViewById(R.id.txtOperator);
        textAnswer = (EditText)findViewById(R.id.txtAnswer);
        textNumPuzzles = (TextView)findViewById(R.id.txtNumPuzzles);

        // Get the number of puzzles to solve (default is 3)
        Intent intent = getIntent();
        numPuzzles = intent.getIntExtra("numPuzles", 3);
        solvedPuzzles = 0;
        generateQuestion();

        // Start alarm
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
        textNumPuzzles.setText("Puzzles: " + solvedPuzzles + " of " + numPuzzles + " solved");
    }


    // Answer button click event
    public void answerButtonClick(View view) {

        // Make sure user entered a number
        if(textAnswer.getText().toString().equals("")) {
            return;
        }


        // Get values
        int num1 = Integer.parseInt(textNum1.getText().toString());
        int num2 = Integer.parseInt(textNum2.getText().toString());
        String operator = textOperator.getText().toString();
        int answer = Integer.parseInt(textAnswer.getText().toString());


        // Check if given answer was correct
        if(answerCheck(num1, num2, operator, answer)) {
            solvedPuzzles++;

            if(solvedPuzzles >= numPuzzles) {
                // Shut off alarm and return to main screen
                r.stop();
                finish();
            } else {
                // Load the next question
                generateQuestion();
                textNumPuzzles.setText("Puzzles: " + solvedPuzzles + " of " + numPuzzles + " solved");
            }
        }

        textAnswer.setText("");
    }


    // Generates a random math question and displays it
    private void generateQuestion() {
        Random r = new Random();
        int num1 = r.nextInt(20);
        int num2 = r.nextInt(20);
        int answer = num1 + num2;
        String operator = "+";

        textNum1.setText(String.valueOf(num1));
        textNum2.setText(String.valueOf(num2));
        textOperator.setText(operator);
    }


    // Checks if the user answered the question correctly
    private boolean answerCheck(int num1, int num2, String operator, int answer) {
        if(num1 + num2 == answer) {
            return true;
        } else {
            return false;
        }
    }
}
