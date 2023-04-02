package com.example.hacatlon.database;

import java.util.ArrayList;
import java.util.List;

public class Questions {

    public static List<String> questions = new ArrayList<>();
    public static  List<String> answers = new ArrayList<>();


    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public static void addQuestions () {

        questions.add("Najważniejszy");
        answers.add("N");

        questions.add("Pani Wanów");
        answers.add("P");

        questions.add("AAA");
        answers.add("AAA");

        questions.add("BBB");
        answers.add("BBB");

    }
}
