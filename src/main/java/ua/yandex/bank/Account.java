package ua.yandex.bank;

/**
 *
 * @author Andrii Hyryla
 */
public class Account {
    
    private final int id;
    private int currentAmount;

    public Account() {
        id = -1;
        currentAmount = 0;
    }

    public Account(int id, int currentAmount) {
        this.id = id;
        this.currentAmount = currentAmount;
    }
    
    public boolean checkForEnoughMoney(int amount) {
        return currentAmount > amount;
    }
    
    public void withdraw(int amount) {
        if (currentAmount > amount) {
            currentAmount -= amount;
        }
    }
    
    public void deposit(int amount) {
        currentAmount += amount;
    }
    
    public int getCurrentAmount() {
        return currentAmount;
    }
}
