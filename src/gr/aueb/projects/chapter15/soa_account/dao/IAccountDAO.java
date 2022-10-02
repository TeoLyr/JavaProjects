package gr.aueb.projects.chapter15.soa_account.dao;

import gr.aueb.projects.chapter15.soa_account.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountDAO {
    boolean insert(Account account);
    Optional<Account> update(Account account);
    boolean delete(long id);
    Optional<Account> deposit(long id, double amount);
    Optional<Account> withdraw(long id, double amount);
    List<Account> getAllState();
    Optional<Account> getAccountState(long id);
}
