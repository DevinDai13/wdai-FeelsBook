package com.example.wdai_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 *
 * Created by Devin Dai on 30/09/2018
 *
 */


/**
 * This activity page is for showing the user the count of each emotion recorded. it simply get the
 * six text views for display and calls the display method which is used for getting and formatting
 * the information that the user sees.
 * So it is really straightforward
 */

public class Count extends AppCompatActivity {

    String[] optionList = new String[] {"love", "joy", "surprise", "angry", "sadness", "fear"};
    TextView love;
    TextView joy;
    TextView surprise;
    TextView sadness;
    TextView fear;
    TextView anger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        love = (TextView) findViewById(R.id.love);
        joy = (TextView) findViewById(R.id.joy);
        surprise = (TextView) findViewById(R.id.surprise);
        sadness = (TextView) findViewById(R.id.sadness);
        fear = (TextView) findViewById(R.id.fear);
        anger = (TextView) findViewById(R.id.anger);

        /* method calls to display the final count on the activity page */
        display(love, 0, "numLove");
        display(joy, 1, "numJoy");
        display(surprise, 2, "numSurprise");
        display(anger, 3, "numAnger");
        display(sadness, 4, "numSadness");
        display(fear, 5, "numFear");

    }


    /**
     * for receiving and displaying the counter values
     */
    public void display(TextView emotion, int pos, String name) {
        Intent intent = getIntent();
        int count = intent.getIntExtra(name, 0);
        String strCount = Integer.toString(count);
        emotion.setText(optionList[pos] + ": " + strCount);
    }

}
