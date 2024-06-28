public class SavingsAccount implements BankAccount {
    private double balance;

    @Override
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void transfer(double amount, BankAccount targetAccount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }
}