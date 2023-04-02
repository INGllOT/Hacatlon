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

        questions.add("Najwyższe nordyckie bóstwo");
        anwserws.add("Odyn");

        questions.add("Olbrzym, symbol ognia, oszustwa i psot");
        anwserws.add("Loki");

        questions.add("Opiekowała się koszem ze złotymi jabłkami młodości.");
        anwserws.add("Idun");

        questions.add("Ile jest światów w mitologii nordyckiej? (cyfra)");
        anwserws.add("9");

        questions.add("Kto rozpoczął ragnarok");
        anwserws.add("Surtr");

        questions.add("Czy bogowie Nordyccy byli nieśmiertelni?");
        anwserws.add("Nie");

        questions.add("Starsza dynastia bogów nordyckich");
        anwserws.add("Wanowie");




    }
}
