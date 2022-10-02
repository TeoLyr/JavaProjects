package gr.aueb.projects.projects;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TheaterSeatsDemo {
    static Scanner in = new Scanner(System.in);

    static boolean[][] theater = new boolean[12][30];

    public static void main(String[] args) {
        char column;
        int row, option, col;

        do {
            printMenu();
            option = getOption();

            switch (option){
                case 1:
                    column = getColumn();
                    col = column - 65;
                    row = getRow();
                    if (!theater[col][row-1]) {
                        book(column, row-1);
                        System.out.printf("Επιτυχής κράτηση της θέσης %c%d", column, row);
                        System.out.println();
                    } else {
                        System.out.printf("Συγγνώμη, η θέση %c%d είναι ήδη κλεισμένη.", column, row);
                        System.out.println();
                    }
                    break;
                case 2:
                    column = getColumn();
                    col = column - 65;
                    row = getRow();
                    if (theater[col][row-1]) {
                        cancel(column, row-1);
                        System.out.printf("Επιτυχής ακύρωση κράτησης της θέσης %c%d.", column, row);
                        System.out.println();
                    } else {
                        System.out.printf("Η θέση %c%d δεν είναι κλεισμένη, οπότε δεν γίνεται να ακυρωθεί.", column, row);
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Έξοδος");
                    break;
                default:
                    System.out.println("Παρακαλώ δώστε έγκυρη τιμή.");
                    System.out.println();
            }
        }while (option != 3);
    }

    public static char getColumn(){
        char column;
        int col;

        while(true) {
            System.out.println("Παρακαλώ δώστε στήλη (A-L) της θέσης");
            column = in.next().charAt(0);
            column = Character.toUpperCase(column);
            col = column - 65;
            if (col < 12 && col >= 0){
                break;
            } else {
                System.out.println();
                System.out.println("Παρακαλώ δώστε έγκυρη τιμή.");
            }
        }
        return column;
    }

    public static int getRow(){
        int row;

        while(true) {
            System.out.println("Παρακαλώ δώστε σειρά (1-30) της θέσης");
            row = in.nextInt();
            if (row <= 30 && row > 0){
                break;
            } else {
                System.out.println();
                System.out.println("Παρακαλώ δώστε έγκυρη τιμή.");
            }
        }
        return row;
    }

    public static int getOption(){
        try {
            return in.nextInt();
        }catch (InputMismatchException e){
            in.nextLine();
            return -1;
        }
    }
    public static void printMenu(){
        System.out.println("Επιλέξτε 1 από τις παρακάτω επιλογές:");
        System.out.println("1. Κράτηση θέσης");
        System.out.println("2. Ακύρωση Κράτησης");
        System.out.println("3. Έξοδος");
    }

    public static void book(char column, int row){
        int col = column - 65;
        theater[col][row] = true;
    }

    public static void cancel(char column, int row){
        int col = column - 65;
        theater[col][row] = false;
    }
}
