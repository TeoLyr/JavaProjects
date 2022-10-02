package gr.aueb.projects.projects;

import java.util.Scanner;

public class LowHighIndexDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = {0, 1, 4, 4, 4, 6, 7, 8, 8, 8, 8, 8};
        int key, lowPos = 0;
        boolean isValid = false;

        System.out.println("Παρακαλώ δώστε ακέραια τιμή για αναζήτηση:");
        key = in.nextInt();

        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]){
                isValid = true;
                lowPos = i;
                break;
            }
        }

        if (isValid == true) {
            System.out.printf("Η τιμή %d υπάρχει στον πίνακα στις θέσεις: %s",key,getLowAndHigIndexOf(arr, key, lowPos));
        } else {
            System.out.printf("Η τιμή %d δεν υπάρχει στον πίνακα.",key);
        }
    }

    public static String getLowAndHigIndexOf(int[] arr, int key, int lowPos){
        int highPos=lowPos;

        for (int i = arr.length - 1; i > lowPos; i--){
            if (arr[i] == arr[lowPos]){
                highPos = i;
                break;
            }
        }

        return "{"+lowPos+","+highPos+"}";
    }
}
