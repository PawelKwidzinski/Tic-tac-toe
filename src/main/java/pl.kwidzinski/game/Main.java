package pl.kwidzinski.game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] output = new String[3][3];
        int movesCommitted = 0;

        TicTacToe ticTacToe = new TicTacToe();

        ticTacToe.printTheGameBoard(output);
        ticTacToe.playTicTacToe(scanner, output, false, movesCommitted);
        ticTacToe.checkingGame(output);
    }






}

