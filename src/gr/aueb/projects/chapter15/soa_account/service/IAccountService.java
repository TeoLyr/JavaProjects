package gr.aueb.projects.chapter15.soa_account.service;

import gr.aueb.projects.chapter15.soa_account.dto.AccountDTO;
import gr.aueb.projects.chapter15.soa_account.model.Account;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountAlreadyExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountNotExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.InsufficientBalanceException;
import gr.aueb.projects.chapter15.soa_account.service.exception.NegativeAmountException;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    boolean insertAccount(AccountDTO accountDTO) throws AccountAlreadyExistsException;
    Optional<Account> updateAccount(AccountDTO accountDTO) throws AccountNotExistsException;
    void deleteAccount(long id) throws AccountNotExistsException;
    void depositAccount(long id, double amount) throws AccountNotExistsException, NegativeAmountException;
    void withdrawAccount(long id, double amount) throws AccountNotExistsException, InsufficientBalanceException;
    double getAccountBalance(long id) throws AccountNotExistsException;
    List<Account> getAllState();
    Optional<Account> getAccountState2(long id) throws AccountNotExistsException;
}
