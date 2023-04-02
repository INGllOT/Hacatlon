package com.example.hacatlon.participants;

import com.example.hacatlon.database.Questions;

import java.util.HashSet;
import java.util.Set;

public class Player {

    public int playerId;
    public int playerPoints;
    public Set<Integer> answeredQuestion = new HashSet<>();
    public int currentQuestion;
    int random_int;

    public Player(int playerId) {
        this.playerId = playerId;
        this.playerPoints = 0;
        lossQuestion();
    }

    public int randomNum() {
        int min = 0; // Minimum value of range
        int max = Questions.questions.size() - 1 ; // Maximum value of range
        int num = (int)Math.floor(Math.random() * (max - min + 1) + min);
        System.out.println("losss number " + num);
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void addPoint() {
        playerPoints ++;
    }
    public void lossQuestion() {

        for(;;) {
             random_int = randomNum();
            //System.out.println("question number " + random_int);
            if(!answeredQuestion.contains(random_int)){
                setCurrentQuestion(random_int);
                break;
            }

        }

    }
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Set<Integer> getAnsweredQuestion() {
        return answeredQuestion;
    }

    public void setAnsweredQuestion(Set<Integer> answeredQuestion) {
        this.answeredQuestion = answeredQuestion;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getRandom_int() {
        return random_int;
    }

    public void setRandom_int(int random_int) {
        this.random_int = random_int;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

}
