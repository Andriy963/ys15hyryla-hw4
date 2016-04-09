package ua.yandex.bank;

import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andrii Hyryla
 */
public class BankTest {
    
    Random random = new Random();
    
    static class BankThread implements Runnable {

        private final Bank bank;
        private final int from;
        private final int to;
        private final int amount;

        public BankThread(Bank bank, int from, int to, int amount) {
            this.bank = bank;
            this.from = from;
            this.to = to;
            this.amount = amount;
        }
        
        @Override
        public void run() {
            bank.transfer(bank.getAccountByIndexInList(from), 
                    bank.getAccountByIndexInList(to), amount);
        }
        
    }
    @Test
    public void transferTestForTenAccountsAndTenThreads() {
        int numbOfAccounts = 10;
        int numbOfThreads = 10;
        int maxValue = 5000;
        Bank bank = new Bank();
        for (int i = 0; i < numbOfAccounts; i++) {
            bank.addNewAccount(random.nextInt(maxValue));
        }
        int startBalance = bank.getBankBalance();
        BankThread[] bth = new BankThread[numbOfThreads];
        Thread[] th = new Thread[numbOfThreads];
        for (int i = 0; i < numbOfThreads; i++) {
            bth[i] = new BankThread(bank, 
                    random.nextInt(numbOfAccounts),
                    random.nextInt(numbOfAccounts),
                    random.nextInt(maxValue));
            
            th[i] = new Thread(bth[i]);
        }
        for (int i = 0; i < numbOfThreads; i++) {
            th[i].start();
        }
        for (int i = 0; i < numbOfThreads; i++) {
            try {
                th[i].join(100);
            } catch (InterruptedException ex) {}
        }
        int endBalance = bank.getBankBalance();
        assertEquals(startBalance, endBalance);
    }
    
    @Test
    public void transferTestForTenAccountsAndThousandThreads() {
        int numbOfAccounts = 10;
        int numbOfThreads = 1000;
        int maxValue = 5000;
        Bank bank = new Bank();
        for (int i = 0; i < numbOfAccounts; i++) {
            bank.addNewAccount(random.nextInt(maxValue));
        }
        int startBalance = bank.getBankBalance();
        BankThread[] bth = new BankThread[numbOfThreads];
        Thread[] th = new Thread[numbOfThreads];
        for (int i = 0; i < numbOfThreads; i++) {
            bth[i] = new BankThread(bank, 
                    random.nextInt(numbOfAccounts),
                    random.nextInt(numbOfAccounts),
                    random.nextInt(maxValue));
            
            th[i] = new Thread(bth[i]);
        }
        for (int i = 0; i < numbOfThreads; i++) {
            th[i].start();
        }
        for (int i = 0; i < numbOfThreads; i++) {
            try {
                th[i].join(100);
            } catch (InterruptedException ex) {}
        }
        int endBalance = bank.getBankBalance();
        assertEquals(startBalance, endBalance);
    }
    
    @Test
    public void transferTestForThousandAccountsAndTenThousandThreads() {
        int numbOfAccounts = 1000;
        int numbOfThreads = 10000;
        int maxValue = 5000;
        Bank bank = new Bank();
        for (int i = 0; i < numbOfAccounts; i++) {
            bank.addNewAccount(random.nextInt(maxValue));
        }
        int startBalance = bank.getBankBalance();
        BankThread[] bth = new BankThread[numbOfThreads];
        Thread[] th = new Thread[numbOfThreads];
        for (int i = 0; i < numbOfThreads; i++) {
            bth[i] = new BankThread(bank, 
                    random.nextInt(numbOfAccounts),
                    random.nextInt(numbOfAccounts),
                    random.nextInt(maxValue));
            
            th[i] = new Thread(bth[i]);
        }
        for (int i = 0; i < numbOfThreads; i++) {
            th[i].start();
        }
        for (int i = 0; i < numbOfThreads; i++) {
            try {
                th[i].join(100);
            } catch (InterruptedException ex) {}
        }
        int endBalance = bank.getBankBalance();
        assertEquals(startBalance, endBalance);
    }
}
