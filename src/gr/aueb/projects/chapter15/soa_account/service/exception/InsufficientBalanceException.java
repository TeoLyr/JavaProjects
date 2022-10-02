package gr.aueb.projects.chapter15.soa_account.service.exception;

import gr.aueb.projects.chapter15.soa_account.model.Account;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(long id){
        super("The account with iban " + id + " has insufficient amount for this transaction.");
    }
}
