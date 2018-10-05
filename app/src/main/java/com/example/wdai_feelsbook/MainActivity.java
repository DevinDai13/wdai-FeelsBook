package com.example.wdai_feelsbook;
/*
 * Copyright (c) 2018. CMPUT301. University of Alberta - All Rights Reserved.
 *  You may use, distribute or modify this code under terms and conditions of Code of Student
 *  Behaviour at University of Alberta.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * Created by Devin Dai on 30/09/2018
 *
 */

/**
 * https://wallpaper-house.com/data/out/7/wallpaper2you_140113.jpg
 * This above image was used for the background of this application
 */

/**
 *  This class controls the activities on the main page of the app. It shows the past records of the
 *  user entered emotions and the user can long click the list of past emotions to edit or delete
 *  emotions. The user can also click the Count Emotion button to see the count of each emotion ever
 *  recorded using the app.
 */

public class MainActivity extends AppCompatActivity {

    //initialize all the variables used in class MainActivity
    private static final String FILENAME = "file.sav";
    public static ListView pastEmotionList;

    public static ArrayList<Emotion> emotionList;
    public static ArrayAdapter<Emotion> adapter;

    int countLove = 0;
    int countJoy = 0;
    int countSurprise = 0;
    int countSadness = 0;
    int countFear = 0;
    int countAnger = 0;

    String[] textList = new String[] {"love", "joy", "surprise", "angry", "sadness", "fear"};

    /**
     * https://stackoverflow.com/questions/12407229/getactivity-cannot-find-symbol-symbol-method-getactivity/12407371
     * Consulted on how to get the MainActivity's context
     */
    private Context mContext = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pastEmotionList = (ListView) findViewById(R.id.PastEmotionList);

        Button loveButton = (Button) findViewById(R.id.loveButton);
        Button joyButton = (Button) findViewById(R.id.joyButton);
        Button surpriseButton = (Button) findViewById(R.id.surpriseButton);
        Button angerButton = (Button) findViewById(R.id.angerButton);
        Button sadButton = (Button) findViewById(R.id.sadButton);
        Button fearButton = (Button) findViewById(R.id.fearButton);
        Button countButton = (Button) findViewById(R.id.countButton);

        /*initialize the context menu for each item in list_view*/
        registerForContextMenu(pastEmotionList);

        /* love button action */
        loveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[0];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                /**
                 * https://www.javatpoint.com/android-toast-example
                 * used for creating Toast text
                 */
                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /*joy button action */
        joyButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[1];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /* surprise button action */
        surpriseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[2];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /*anger button action */
        angerButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[3];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /*sad button action */
        sadButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[4];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /* fear button action */
        fearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = textList[5];
                String timeStamp = returnTime();

                Emotion newEmotion = new Emotion(text, "", timeStamp, 1);
                emotionList.add(newEmotion);
                adapter.notifyDataSetChanged();

                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"New emotion added",Toast.LENGTH_SHORT).show();
            }
        });

        /* go to the count activity to see the count of each emotion */
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countLove = 0;
                countJoy = 0;
                countSurprise = 0;
                countSadness = 0;
                countFear = 0;
                countAnger = 0;
                /* call countEmotion method when count button is clicked */
                countEmotion();
                Intent goToCountActivity = new Intent(getApplicationContext(), Count.class);
                goToCountActivity.putExtra("numLove", countLove);
                goToCountActivity.putExtra("numJoy", countJoy);
                goToCountActivity.putExtra("numSurprise", countSurprise);
                goToCountActivity.putExtra("numSadness", countSadness);
                goToCountActivity.putExtra("numFear", countFear);
                goToCountActivity.putExtra("numAnger", countAnger);
                startActivity(goToCountActivity);
            }
        });
    }

    /* create a new object for the alert dialog */
    final WindowPop windowpop = new WindowPop(this);

    /* context menu for each item in past emotion list */

    /**
     * https://www.youtube.com/watch?v=nEt2soffl5Y by Tihomir RAdeff, retrieved on 30/09/2018
     * Watched the above youtube video on how to create a context menu
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch(item.getItemId()){

            /* when delete option is clicked */
            case R.id.delete:
                // get content of the item user clicked at that position
                String clickedItemContent = emotionList.get(position).getEmotion();

                if (compareStr(clickedItemContent, textList[0])){
                    countLove -= emotionList.get(position).getValue();
                } else if (compareStr(clickedItemContent, textList[1])){
                    countJoy -= emotionList.get(position).getValue();
                } else if (compareStr(clickedItemContent, textList[2])){
                    countSurprise -= emotionList.get(position).getValue();
                } else if (compareStr(clickedItemContent, textList[3])){
                    countAnger -= emotionList.get(position).getValue();
                } else if (compareStr(clickedItemContent, textList[4])){
                    countSadness -= emotionList.get(position).getValue();
                } else if (compareStr(clickedItemContent, textList[5])){
                    countFear -= emotionList.get(position).getValue();
                }
                emotionList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile(mContext);

                Toast.makeText(getApplicationContext(),"Emotion deleted",Toast.LENGTH_SHORT).show();
                return true;

            /* the pop window appears for editing when edit is clicked */
            case R.id.edit:
                windowpop.editEmotion(position);

            default:
                return super.onContextItemSelected(item);
        }
    }

    /* Compares the first 2 characters */
    static boolean compareStr(String s1, String s2) {
        return s1.regionMatches(0, s2, 0, 2);
    }


    /* a for loop that counts the number of each emotion in the past emotion list */
    public void countEmotion(){
        for (int i = 0; i < emotionList.size(); i++) {
            if(compareStr(textList[0], emotionList.get(i).getEmotion()))
                countLove += emotionList.get(i).getValue();
            else if(compareStr(textList[1], emotionList.get(i).getEmotion()))
                countJoy += emotionList.get(i).getValue();
            else if(compareStr(textList[2], emotionList.get(i).getEmotion()))
                countSurprise += emotionList.get(i).getValue();
            else if(compareStr(textList[3], emotionList.get(i).getEmotion()))
                countAnger += emotionList.get(i).getValue();
            else if(compareStr(textList[4], emotionList.get(i).getEmotion()))
                countSadness += emotionList.get(i).getValue();
            else if(compareStr(textList[5], emotionList.get(i).getEmotion()))
                countFear += emotionList.get(i).getValue();
        }
    }

    /* return the timestamp for past emotion list */
    public String returnTime(){
        Date date = new Date(System.currentTimeMillis());
        String day = String.format("%tFT", date);
        String hour = String.format("%tT", date);
        String currentTime = day + hour;
        return currentTime;
    }

    /**
     * --The following code was taken from lonelyTwitter (written by Joshua Carles Campbell) with some modifications
     * --onStart, loadFromFile, saveInFile are for saving the past emotion list and their corresponding
     * --emotion name, timestamp and comment in a file for reopening after app has been closed
    **/

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Emotion>(this, R.layout.list_item, emotionList);
        pastEmotionList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            emotionList = new ArrayList<Emotion>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}