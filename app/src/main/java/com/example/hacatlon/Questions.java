package com.example.hacatlon;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Questions {

    public static  ArrayList<String> questions = new ArrayList<>();
    public static  ArrayList<String> anwserws = new ArrayList<>();


    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getAnwserws() {
        return anwserws;
    }



    public void setAnwserws(ArrayList<String> anwserws) {
        this.anwserws = anwserws;
    }

    public static void addQuestions () {

        questions.add("Najważniejszy");
        anwserws.add("N");

        questions.add("Pani Wanów");
        anwserws.add("P");

        questions.add("AAA");
        anwserws.add("AAA");

        questions.add("BBB");
        anwserws.add("BBB");

    }
}
