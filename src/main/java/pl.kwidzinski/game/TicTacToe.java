package pl.kwidzinski.game;

import java.util.Scanner;

public class TicTacToe {

    void printTheGameBoard(String[][] output) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                output[i][j] = " ";
                System.out.printf("%s ", output[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

     String checkingGame(String[][] output) {
        String winner = null;
        int xCount = 0;
        int oCount = 0;
        int xBackDiagonal = 0;
        int oBackDiagonal = 0;
        int xDiagonal = 0;
        int oDiagonal = 0;

        boolean xWin = false;
        boolean oWin = false;
        boolean impossible = false;

        for (int i = 0; i < 3; i++) {
            int xRow = 0;
            int oRow = 0;
            int xColumn = 0;
            int oColumn = 0;
            for (int j = 0; j < 3; j++) {
                //check row
                if (output[i][j].equals("X")) {
                    xRow++;
                    xCount++; // Total X entered
                }
                if (output[i][j].equals("O")) {
                    oRow++;
                    oCount++; // Total O entered
                }
                //check column
                if (output[j][i].equals("X")) {
                    xColumn++;
                } else if (output[j][i].equals("O")) {
                    oColumn++;
                }
            }
            //check diagonal this "\" way
            if (output[i][i].equals("X")) {
                xBackDiagonal++;
            }
            if (output[i][i].equals("O")) {
                oBackDiagonal++;
            }
            //check diagonal this "/" way
            if (output[i][2 - i].equals("X")) {
                xDiagonal++;
            }
            if (output[i][2 - i].equals("O")) {
                oDiagonal++;
            }
            //check winning stage
            if (xRow == 3 || xColumn == 3 || xDiagonal == 3 || xBackDiagonal == 3) {
                xWin = true;
            }
            if (oRow == 3 || oColumn == 3 || oDiagonal == 3 || oBackDiagonal == 3) {
                oWin = true;
            }
        }
        if (Math.abs(xCount - oCount) > 1 || xWin && oWin) {
            impossible = true;
            winner = "Impossible";
        }
        if (xWin && !oWin) {
            winner = "X wins";
        }
        if (!xWin && oWin) {
            winner = "O wins";
        }
        if (!xWin && !oWin && xCount + oCount == 9) {
            winner = "Draw";
        }
        /*if (!xWin && !oWin && xCount + oCount < 9) {
            winner = "Game not finished";
        }*/
        return winner;
    }

    void playTicTacToe(Scanner scanner, String[][] output, boolean play, int movesCommitted) {
        String winner;
        do {
            System.out.print("Enter the coordinates: ");

            String symbol;
            String coordinate = scanner.nextLine();
            String[] stringsCoordinates = coordinate.split(" ");
            int size = stringsCoordinates.length;
            int[] intCoordinates = new int[size];

            for (int i = 0; i < size; i++) {
                try {
                    intCoordinates[i] = Integer.parseInt(stringsCoordinates[i]);
                    play = true;
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    play = false;
                    break;
                }
            }
            if (play && intCoordinates[0] > 3 || intCoordinates[0] < 1 || intCoordinates[1] > 3 || intCoordinates[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                play = false;
            }

            if (play) {
                if (movesCommitted % 2 == 0) {
                    symbol = "X";
                } else {
                    symbol = "O";
                }

                if (output[3 - intCoordinates[1]][intCoordinates[0] - 1].equals(" ")) {
                    output[3 - intCoordinates[1]][intCoordinates[0] - 1] = symbol;
                    movesCommitted++;
                    System.out.println("---------");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("| ");
                        for (int j = 0; j < 3; j++) {
                            System.out.printf("%s ", output[i][j]);
                        }
                        System.out.println("| ");
                    }
                    System.out.println("---------");
                    winner = checkingGame(output);
                    if (winner != null && winner.equals("X wins")) {
                        System.out.println("X wins");
                        break;
                    } else if (winner != null && winner.equals("O wins")) {
                        System.out.println("O wins");
                        break;
                    } else if (winner != null && winner.equals("Draw")) {
                        System.out.println("Draw");
                        break;
                    } /*else {
                        System.out.println(winner);
                    }*/

                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
            play = true;
            //System.out.println(winner);
        }
        while (play);
    }
}
