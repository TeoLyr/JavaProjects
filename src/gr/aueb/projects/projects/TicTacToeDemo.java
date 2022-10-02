package gr.aueb.projects.projects;

import java.util.Scanner;

public class TicTacToeDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] board = new char[3][3];
        int inputRow, inputColumn;
        char mark = 'o';
        boolean end = false;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = '-';
            }
        }

        do{
            if (mark == 'o'){
                mark = 'x';
                System.out.println();
                System.out.println("Παίχτης 1:");
            } else {
                mark = 'o';
                System.out.println();
                System.out.println("Παίχτης 2:");
            }
            System.out.println("Παρακαλώ δώστε γραμμή:");
            inputRow = in.nextInt();
            System.out.println("Παρακαλώ δώστε στήλη:");
            inputColumn = in.nextInt();
            while (board[inputRow-1][inputColumn-1] != '-'){
                System.out.println("Υπάρχει ήδη σύμβολο σε αυτή τη θέση.");
                System.out.println("Παρακαλώ δώστε γραμμή(1-3):");
                inputRow = in.nextInt();
                System.out.println("Παρακαλώ δώστε στήλη(1-3):");
                inputColumn = in.nextInt();
            }

            board[inputRow-1][inputColumn-1] = mark;

            for (int i = 0; i < 3; i++){
                System.out.println(board[i][0] + " " + board[i][1] + " " + board[i][2]);
            }

            if (hasWon(board)){
                if (mark == 'x'){
                    System.out.println();
                    System.out.println("O Παίκτης 1 κέρδισε!");
                    end = true;
                } else {
                    System.out.println();
                    System.out.println("Ο Παίκτης 2 κέρδισε!");
                    end = true;
                }
            }
            if (hasTied(board)){
                System.out.println();
                System.out.println("Ισοπάλία.");
                end = true;
            }

        }while (!end);

    }

   public static boolean hasWon(char[][] board){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != '-') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != '-') {
                return true;
            }
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '-') {
                return true;
            }
            if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != '-') {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTied(char[][] board){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == '-' ){
                    return false;
                }
            }
        }
        return true;
    }
}
