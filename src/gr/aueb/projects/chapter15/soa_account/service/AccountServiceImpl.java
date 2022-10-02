package gr.aueb.projects.chapter15.soa_account.service;

import gr.aueb.projects.chapter15.soa_account.dao.IAccountDAO;
import gr.aueb.projects.chapter15.soa_account.dto.AccountDTO;
import gr.aueb.projects.chapter15.soa_account.model.Account;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountAlreadyExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.AccountNotExistsException;
import gr.aueb.projects.chapter15.soa_account.service.exception.InsufficientBalanceException;
import gr.aueb.projects.chapter15.soa_account.service.exception.NegativeAmountException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements IAccountService{
    private final IAccountDAO dao;

    public AccountServiceImpl(IAccountDAO contactDAO) {
        this.dao = contactDAO;
    }

    @Override
    public boolean insertAccount(AccountDTO accountDTO) throws AccountAlreadyExistsException {
        Account account = convertDTO(accountDTO);
        try{
            if (dao.insert(account)){
                return true;
            } else {
                throw new AccountAlreadyExistsException(account);
            }
        } catch (AccountAlreadyExistsException e){
            System.err.println("Contact already exists exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public Optional<Account> updateAccount(AccountDTO accountDTO) throws AccountNotExistsException {
        try {
            Account account = convertDTO(accountDTO);
            Optional<Account> optionalContact = dao.update(account);

            if (optionalContact.isEmpty()) {
                throw new AccountNotExistsException(account);
            } else {
                return optionalContact;
            }
        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public void deleteAccount(long id) throws AccountNotExistsException {
        try {
            if (!dao.delete(id)){
                throw new AccountNotExistsException(id);
            }
        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public void depositAccount(long id, double amount) throws AccountNotExistsException, NegativeAmountException {
        try {
            if (amount <= 0){
                throw new NegativeAmountException();
            }
            Optional<Account> optionalAccount = dao.deposit(id, amount);

            if (optionalAccount.isEmpty()) {
                throw new AccountNotExistsException(id);
            }
        } catch (NegativeAmountException e){
            System.err.println("Negative amount exception " + LocalDateTime.now());
            throw e;
        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public void withdrawAccount(long id, double amount) throws AccountNotExistsException, InsufficientBalanceException {
        try {

            Optional<Account> optionalAccount = dao.withdraw(id, amount);

            if (optionalAccount.isEmpty()) {
                throw new AccountNotExistsException(id);
            }

            if (optionalAccount.get().getBalance() < amount) {
                throw new InsufficientBalanceException(id);
            }

        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        } catch (InsufficientBalanceException e){
            System.err.println("Account balance is insufficient exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public double getAccountBalance(long id) throws AccountNotExistsException {
        try {
            Optional<Account> account = dao.getAccountState(id);
            if (account.isPresent()){
                return account.get().getBalance();
            } else {
                throw new AccountNotExistsException(id);
            }
        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        }
    }

    @Override
    public List<Account> getAllState() {
        return dao.getAllState();
    }

    @Override
    public Optional<Account> getAccountState2(long id) throws AccountNotExistsException {
        try {
            Optional<Account> account = dao.getAccountState(id);
            if (account.isPresent()){
                return account;
            } else {
                throw new AccountNotExistsException(id);
            }
        } catch (AccountNotExistsException e){
            System.err.println("Account does not exist exception " + LocalDateTime.now());
            throw e;
        }
    }

    private Account convertDTO(AccountDTO accountDTO){
        return new Account(accountDTO.getId(), accountDTO.getIban(), accountDTO.getLastname(), accountDTO.getFirstname(), accountDTO.getSsn(), accountDTO.getBalance() );
    }
}
