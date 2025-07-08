public class Customer extends Person {
    private double balance;

    public Customer(double balance, String name, String email) {
        super(name, email);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}