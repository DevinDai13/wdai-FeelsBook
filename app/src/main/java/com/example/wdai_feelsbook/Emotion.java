package com.example.wdai_feelsbook;

import java.util.Comparator;

/**
 *
 * Created by Devin Dai on 30/09/2018
 *
 */


/**
 * --Emotion class for storing all the info a emotion record needs such as the name of emotion,
 * comments, timestamp and the value(which always equals to 1 for counting emotion purpose)
 * --This class basically has all these self explanatory getters and setters for returning and receiving aspects of
 * each emotion record.
 * --There is also the comparator which is used for sorting with Collection.sort of ArrayList with of
 * a custom object type.
 */



public class Emotion {
    private String emotion;
    private String comment;
    private String date;
    private int value;


    public Emotion(String emotion, String comment, String date, int value){
        this.emotion = emotion;
        this.comment = comment;
        this.date = date;
        this.value = value;
    }

    public String getEmotion() {
        return emotion;}

    public void setEmotion(String emotion){

        this.emotion = emotion;
    }

    public String getComment() {
        return comment;}

    public void setComment(String comment) {

        this.comment = comment;
    }

    public int getValue(){
        return value;
    }

    public String getDate() {
        return this.date;}

    public void setDate(String date) {

        this.date = date;
    }

    /**
     * https://www.javadevjournal.com/java/java-sorting-example-comparable-comparator/
     * Consulted on how to sort ArrayList with custom object from the link above
     */

    public static Comparator<Emotion> sortComparator = new Comparator<Emotion>() {

        public int compare(Emotion emo1, Emotion emo2) {

            return emo1.getDate().compareTo(emo2.getDate());
        }};



    @Override
    //using this displays the name + date
    public String toString() {
        return date + " | " + emotion;
    }


}

