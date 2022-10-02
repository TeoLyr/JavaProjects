package gr.aueb.projects.chapter15.soa_account.service.exception;

import gr.aueb.projects.chapter15.soa_account.model.Account;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException(Account account){
        super("Account with id: " + account.getId() + " already exists");
    }
}
