package gr.aueb.projects.chapter15.soa_account;

import gr.aueb.projects.chapter15.soa_account.dao.AccountDAOImpl;
import gr.aueb.projects.chapter15.soa_account.dao.IAccountDAO;
import gr.aueb.projects.chapter15.soa_account.dto.AccountDTO;
import gr.aueb.projects.chapter15.soa_account.model.Account;
import gr.aueb.projects.chapter15.soa_account.service.AccountServiceImpl;
import gr.aueb.projects.chapter15.soa_account.service.IAccountService;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountAlreadyExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountNotExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.InsufficientBalanceException;
import gr.aueb.projects.chapter15.soa_account.service.exception.NegativeAmountException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final IAccountDAO contactDAO = new AccountDAOImpl();
    private static final IAccountService service = new AccountServiceImpl(contactDAO);

    public static void main(String[] args) {
        int choice = 0;
        AccountDTO accountDTO;
        try {
            AccountDTO teo= new AccountDTO(1, "1234", "Lyr", "Teo", "0987", 1000);
            service.insertAccount(teo);
        } catch (AccountAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

        do {
            printMenu();
            choice = getChoice();
            switch (choice) {
                case 1:
                    try {
                        accountDTO = new AccountDTO(getId(),getIban(),getLastname(),getFirstname(),getSsn(),getBalance());
                        service.insertAccount(accountDTO);
                    } catch (AccountAlreadyExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try{
                        accountDTO = new AccountDTO(getId(),getIban(),getLastname(),getFirstname(),getSsn(),getBalance());
                        Optional<Account> optionalAccount = service.updateAccount(accountDTO);
                    } catch (AccountNotExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        long id = getId();
                        service.deleteAccount(id);
                    } catch (AccountNotExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        long id = getId();
                        double amount = getAmount();
                        service.depositAccount(id, amount);
                    } catch (NegativeAmountException | AccountNotExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        long id = getId();
                        double amount = getAmount();
                        service.withdrawAccount(id, amount);
                    } catch (InsufficientBalanceException | AccountNotExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        long id = getId();
                        System.out.println(service.getAccountBalance(id));
                    } catch (AccountNotExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    List<Account> contacts = service.getAllState();
                    contacts.forEach(System.out::println);
                    break;
                case 8:
                    try {
                        long id = getId();
                        Optional<Account> account = service.getAccountState2(id);
                        account.ifPresent(System.out::println);
                    } catch (AccountNotExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Έξοδος");
                    break;
                default:
                    System.out.println("Επιλέξτε 1-9");
                    break;
            }
        } while (choice != 9);
    }

    public static void printMenu(){
        System.out.println("Επιλέξτε ένα από τα παρακάτω:");
        System.out.println("1. Εισαγωγή λογαριασμού");
        System.out.println("2. Ενημέρωση λογαριασμού");
        System.out.println("3. Διαγραφή λογαριασμού");
        System.out.println("4. Κατάθεση σε λογαριασμό");
        System.out.println("5. Ανάληψη από λογαριασμό");
        System.out.println("6. Ενημέρωση υπολοίπου");
        System.out.println("7. Εκτύπωση όλων των λογαριασμών");
        System.out.println("8. Αναζήτηση λογαριασμού");
        System.out.println("9. Έξοδος");
    }

    public static int getChoice(){
        String s = in.next();
        if (isInt(s)) return Integer.parseInt(s);
        return -1;
    }

    public static String getFirstname(){
        System.out.println("Εισάγετε Όνομα:");
        return in.next();
    }

    public static String getLastname(){
        System.out.println("Εισάγετε Επώνυμο:");
        return in.next();
    }

    public static int getId(){
        System.out.println("Εισάγετε Κωδικό:");
        String s = in.next();
        if (isInt(s)) return Integer.parseInt(s);
        return -1;
    }

    public static String getIban(){
        System.out.println("Εισάγετε Iban:");
        return in.next();
    }

    public static String getSsn(){
        System.out.println("Εισάγετε ssn:");
        return in.next();
    }

    public static double getBalance(){
        System.out.println("Εισάγετε υπόλοιπο:");
        String s = in.next();
        if (isDouble(s)) return Double.parseDouble(s);
        return -1;
    }

    public static double getAmount(){
        System.out.println("Εισάγετε ποσό:");
        String s = in.next();
        if (isDouble(s)) return Double.parseDouble(s);
        return -1;
    }

    private static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
