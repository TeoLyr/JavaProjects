package gr.aueb.projects.chapter15.soa_account.dao;

import gr.aueb.projects.chapter15.soa_account.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements IAccountDAO{

    private static final List<Account> accounts = new ArrayList<>();


    @Override
    public boolean insert(Account account) {
        if (account == null) return false;
        if (accounts.contains(account)) return false;
        accounts.add(account);
        return true;
    }

    @Override
    public Optional<Account> update(Account account) {
        if (account == null) return Optional.empty();
        int positionToUpdate = getPosition(account.getId());
        if (positionToUpdate != -1){
            Account accountToReturn = accounts.get(positionToUpdate);
            accounts.set(positionToUpdate, account);
            return Optional.of(accountToReturn);
        }

        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        int positionToDelete = getPosition(id);

        if (positionToDelete != -1){
            accounts.remove(positionToDelete);
            return true;
        }

        return false;
    }

    @Override
    public Optional<Account> deposit(long id, double amount) {
        int positionToDeposit = getPosition(id);
        if (positionToDeposit != -1) {
            Account accountToReturn = accounts.get(positionToDeposit);
            accountToReturn.setBalance(accountToReturn.getBalance() + amount);
            return Optional.of(accountToReturn);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Account> withdraw(long id, double amount) {
        int positionToDeposit = getPosition(id);
        if (positionToDeposit != -1){
            Account accountToReturn = accounts.get(positionToDeposit);
            if (amount <= accountToReturn.getBalance()){
            accountToReturn.setBalance(accountToReturn.getBalance() - amount);
            return Optional.of(accountToReturn);
            }
            return Optional.of(accountToReturn);
        }

        return Optional.empty();
    }

    @Override
    public List<Account> getAllState() { return new ArrayList<Account>(accounts); }

    @Override
    public Optional<Account> getAccountState(long id) {
        int positionToReturn = getPosition(id);
        if(positionToReturn != -1){
            return Optional.of(accounts.get(positionToReturn));
        }
        return Optional.empty();
    }

    private int getPosition(long id) {
        int positionToReturn = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }
}
