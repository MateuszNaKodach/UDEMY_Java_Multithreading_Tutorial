package lecture11deadlock;

/**
 * Created by Mateusz on 12.01.2017.
 */
public class Account {
    private int balance = 10000;

    public void deposit(int amount){
        balance+=amount;
    }

    public void withdraw(int amount){
        balance-=amount;
    }

    public int getBalance(){
        return balance;
    }

    public static void transfer(Account src, Account dest, int amount){
        src.withdraw(amount);
        dest.deposit(amount);
    }
}
