package com.example.wdai_feelsbook;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.Date;

/**
 *
 * Created by Devin Dai on 30/09/2018
 *
 */

/**
 * --Most of the code for initializing alert dialog is the universal way of setting it up so I
 * just followed the tutorials in the following links to build one that fits my needs.
 * https://developer.android.com/reference/android/app/Dialog
 * https://developer.android.com/guide/topics/ui/dialogs#java
 * https://www.tutorialspoint.com/android/android_alert_dialoges.htm
 * https://www.mkyong.com/android/android-prompt-user-input-dialog-example/
 *
 * --Also consulted Steven Yan @ ssyan@ualberta.ca on the idea of using Alert dialogs instead of
 * another activity which was my original idea for the editing process.
 *
 * --This class is for setting up and initializing the pop up alert dialog for editing a selected
 * past emotion and saving the changes including error checking for the user inputs.
 *
 * */

public class WindowPop {

    private Context context;
    String[] optionList = new String[] {"love", "joy", "surprise", "angry", "sadness", "fear"};

    public WindowPop(Context context) {
        this.context = context;
    }


    public void editEmotion(final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.edit_emotion, null);

        final EditText editEmotion = (EditText) promptView.findViewById(R.id.editEmotion);
        final EditText editTime = (EditText) promptView.findViewById(R.id.editTime);
        final EditText editComment = (EditText) promptView.findViewById(R.id.editComment);

        Date date = new Date(System.currentTimeMillis());
        String day = String.format("%tFT", date);
        String hour = String.format("%tT", date);
        final String currentTime = day + hour;

        final Emotion emotion = MainActivity.emotionList.get(position);

        editEmotion.setText(emotion.getEmotion());
        editTime.setText(emotion.getDate());
        editComment.setText(emotion.getComment());

        final AlertDialog ad = new AlertDialog.Builder(context)
                .setView(promptView)
                .setTitle("Edit Past Emotion")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button okButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* error checking of the user inputs for the editing alert dialog */
                        if (editEmotion.getText().toString().trim().length() == 0) {
                            editEmotion.setError("Please enter an emotion!");
                        }
                        else if (!editEmotion.getText().toString().equals(optionList[0]) &&
                                !editEmotion.getText().toString().equals(optionList[1]) &&
                                !editEmotion.getText().toString().equals(optionList[2]) &&
                                !editEmotion.getText().toString().equals(optionList[3]) &&
                                !editEmotion.getText().toString().equals(optionList[4]) &&
                                !editEmotion.getText().toString().equals(optionList[5])) {
                            editEmotion.setError("Emotion not available!");
                        }
                        else if (editComment.getText().toString().trim().length() > 100) {
                            editComment.setError("Comment is limited to 100 characters!");
                        } else if (editTime.getText().toString().trim().length() == 0) {
                            editTime.setError("Please enter a date!");
                        }

                        /* The user is not allowed to enter time into the future as that does not make sense in reality */
                        else if (editTime.getText().toString().compareTo(currentTime) == 1 ) {
                            editTime.setError("Please enter an appropriate date!");
                        }
                        else {
                            emotion.setEmotion(editEmotion.getText().toString());
                            emotion.setDate(editTime.getText().toString());
                            emotion.setComment(editComment.getText().toString());


                             /* after each edit, we sort the list view based on the date of each emotion */

                            Collections.sort(MainActivity.emotionList, Emotion.sortComparator);

                            MainActivity.adapter.notifyDataSetChanged();
                            MainActivity.saveInFile(context);
                            ad.dismiss();
                            Toast.makeText(context,"Emotion edits saved",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        ad.show();
    }
}