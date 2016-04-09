package ua.yandex.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Andrii Hyryla
 */
public class Bank {

    private final Lock lock = new ReentrantLock();
    private final Condition notEnoughMoney = lock.newCondition();
    private final List<Account> accounts = new ArrayList<>();

    public void transfer(Account from, Account to, int amount) {
        lock.lock();
        try {
            while (!from.checkForEnoughMoney(amount)) {
                try {
                    notEnoughMoney.await();
                } catch (InterruptedException ex) {
                }
            }
            from.withdraw(amount);
            to.deposit(amount);
            notEnoughMoney.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public void addNewAccount(int amount) {
        accounts.add(new Account(accounts.size(), amount));
    }
    
    public int getBankBalance() {
        int sum = 0;
        for (Account account : accounts) {
            sum += account.getCurrentAmount();
        }
        return sum;
    }
    
    public Account getAccountByIndexInList(int index) {
        return accounts.get(index);
    }
}
