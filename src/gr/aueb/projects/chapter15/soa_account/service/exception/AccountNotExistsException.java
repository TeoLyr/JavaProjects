package gr.aueb.projects.chapter15.soa_account.service.exception;

import gr.aueb.projects.chapter15.soa_account.model.Account;

public class AccountNotExistsException extends Exception {
    public AccountNotExistsException(Account account){
        super("Account with id = " + account.getId() + " does not exist");
    }

    public AccountNotExistsException(Long id){
        super("Account with id = " + id + " does not exist");
    }
}
