package gr.aueb.projects.chapter15.soa_account.service.exception;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(){
        super("Amount given for deposit is negative");
    }
}
