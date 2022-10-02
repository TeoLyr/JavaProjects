package gr.aueb.projects.projects;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactsAppDemo {
    static String[][] contacts = new String[500][3];
    static int top = -1;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        String lastname = "";
        String firstname = "";
        String phoneNumber;
        String newLastname;
        String newFirstname;
        String newPhoneNumber;

        do {
            printMenu();
            option = getOption();

            switch (option){
                case 1:
                    phoneNumber = getPhoneNumber();
                    if (itemExists(contacts, phoneNumber)) {
                        System.out.printf("Ο αριθμός %s ανήκει στον/στην %s %s\n",phoneNumber, contacts[getPosition(contacts, phoneNumber)][0], contacts[getPosition(contacts, phoneNumber)][1]);
                    } else {
                        System.out.printf("Ο αριθμός %s δεν υπάρχει στις επαφές\n", phoneNumber);
                    }
                    break;
                case 2:
                    lastname = getLastname();
                    firstname = getFirstname();
                    phoneNumber = getPhoneNumber();
                    if(getPosition(contacts, phoneNumber) != -1){
                        System.out.println("Η επαφή υπάρχει ήδη");
                    } else{
                        insert(contacts, lastname, firstname, phoneNumber);
                        System.out.println("Επιτυχής Εισαγωγή");
                    }

                    break;
                case 3:
                    phoneNumber = getPhoneNumber();
                    if (getPosition(contacts, phoneNumber) == -1){
                        System.out.println("Δεν βρέθηκε η επαφή");
                    } else {
                        newLastname = getLastname();
                        newFirstname = getFirstname();
                        newPhoneNumber = getPhoneNumber();
                        replace(contacts, phoneNumber, newLastname, newFirstname, newPhoneNumber);
                        System.out.println("Επιτυχής Ενημέρωση");
                    }
                        break;

                case 4:
                    phoneNumber = getPhoneNumber();
                    if (getPosition(contacts, phoneNumber) == -1){
                        System.out.println("Δεν βρέθηκε η επαφή");
                    } else {
                        delete(contacts, phoneNumber);
                        System.out.println("Επιτυχής Διαγραφή");
                    }
                    break;
                case 5:
                    System.out.println("Έξοδος");
                    break;
                case 6:
                    printItems(contacts);
                default:
                    System.out.println("Παρακαλώ δώστε έγκυρη τιμή.");
                    System.out.println();
            }
        }while (option != 5);
    }

    public static void printMenu(){
        System.out.println("Επιλέξτε 1 από τις παρακάτω επιλογές:");
        System.out.println("1. Αναζήτηση Επαφής (με βαση το τηλεφωνο)");
        System.out.println("2. Εισαγωγή Επαφής");
        System.out.println("3. Ενημέρωση Επαφής");
        System.out.println("4. Διαγραφή Επαφής");
        System.out.println("5. Έξοδος");
    }

    public static int getOption(){
        try {
            return in.nextInt();
        }catch (InputMismatchException e){
            in.nextLine();
            System.out.println("Παρακαλώ δώστε έγκυρη τιμή.");
            return -1;
        }
    }
    public static String getLastname(){
        System.out.println("Παρακαλώ δώσε επώνυμο:");
        return in.next();
    }

    public static String getFirstname(){
        System.out.println("Παρακαλώ δώσε όνομα:");
        return in.next();
    }

    public static String getPhoneNumber(){
        System.out.println("Παρακαλώ δώσε τηλεφωνικό αριθμό:");
        return in.next();
    }

    public static boolean itemExists(String[][] arr, String phone){
        return (getPosition(arr, phone) != -1);
    }


    public static void insert(String[][] arr, String last, String name, String phone){
        if (arr == null || last == null || name == null || phone == null) return;
        if (last.equals("")) return;
        if (name.equals("")) return;
        if (phone.equals("")) return;

        if(getPosition(arr, phone) == -1){
            top++;
            arr[top][0] = last;
            arr[top][1] = name;
            arr[top][2] = phone;
        }
    }

    public static int getPosition(String[][] arr, String phone){
        if (arr == null || phone == null) return -1;
        if (phone.equals("")) return -1;

        for (int i = 0; i <= top; i++){
                if (arr[i][2].equals(phone)) {
                    return i;
            }
        }

        return -1;
    }

    public static void replace(String[][] arr, String oldNumber, String newLast, String newName, String newPhone){
        if (arr == null || newName == null || oldNumber == null || newLast == null || newPhone == null) return;
        if (newName.equals("")) return;
        if (oldNumber.equals("")) return;
        if (newLast.equals("")) return;
        if (newPhone.equals("")) return;

        int positionToUpdate = getPosition(arr, oldNumber);

        arr[positionToUpdate][0] = newLast;
        arr[positionToUpdate][1] = newName;
        arr[positionToUpdate][2] = newPhone;

    }

    public static String delete(String[][] arr, String phone){
        if (arr == null || phone == null) return "";
        if (phone.equals("")) return "";

        int positionToDelete = getPosition(arr, phone);
        String toBeReturned = "";

        toBeReturned = arr[positionToDelete][2];
        System.arraycopy(arr, positionToDelete + 1, arr, positionToDelete, (top - positionToDelete));
        top--;

        return toBeReturned;
    }

    public static void printItems(String[][] arr){
        for (int i = 0; i <= top; i++){
            for (int j = 0; j < 3; j++) {
                System.out.println(arr[i][j]);
            }
        }
    }
}
