package com.example.hacatlon;

import java.util.HashSet;


public class Players {

    public static Player player1 = new Player(1);
    public static Player player2 = new Player(2);
    public static boolean player1win = true;

    public static String winner = "NONE";

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        Players.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        Players.player2 = player2;
    }

    public static boolean isPlayer1win() {
        return player1win;
    }

    public static void setPlayer1win(boolean player1win) {
        Players.player1win = player1win;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        Players.winner = winner;
    }
}
