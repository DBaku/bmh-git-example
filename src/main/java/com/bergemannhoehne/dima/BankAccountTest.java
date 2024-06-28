public class BankAccountTest {
    public static void main(String[] args) {
        testDeposit();
        testWithdraw();
        testGetBalance();
        testTransfer();
    }

    private static void testDeposit() {
        BankAccount account = new SavingsAccount();

        // Test positive deposit
        account.deposit(100.0);
        if (account.getBalance() == 100.0) {
            System.out.println("testDeposit positive amount: PASSED");
        } else {
            System.out.println("testDeposit positive amount: FAILED");
        }

        // Test negative deposit
        try {
            account.deposit(-50.0);
            System.out.println("testDeposit negative amount: FAILED");
        } catch (IllegalArgumentException e) {
            System.out.println("testDeposit negative amount: PASSED");
        }
    }

    private static void testWithdraw() {
        BankAccount account = new SavingsAccount();
        account.deposit(200.0);

        // Test positive withdraw
        try {
            account.withdraw(50.0);
            if (account.getBalance() == 150.0) {
                System.out.println("testWithdraw positive amount: PASSED");
            } else {
                System.out.println("testWithdraw positive amount: FAILED");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("testWithdraw positive amount: FAILED");
        }

        // Test negative withdraw
        try {
            account.withdraw(-30.0);
            System.out.println("testWithdraw negative amount: FAILED");
        } catch (IllegalArgumentException e) {
            System.out.println("testWithdraw negative amount: PASSED");
        } catch (InsufficientFundsException e) {
            System.out.println("testWithdraw negative amount: FAILED");
        }

        // Test insufficient funds withdraw
        try {
            account.withdraw(300.0);
            System.out.println("testWithdraw insufficient funds: FAILED");
        } catch (InsufficientFundsException e) {
            System.out.println("testWithdraw insufficient funds: PASSED");
        }
    }

    private static void testGetBalance() {
        BankAccount account = new SavingsAccount();

        // Test initial balance
        if (account.getBalance() == 0.0) {
            System.out.println("testGetBalance initial: PASSED");
        } else {
            System.out.println("testGetBalance initial: FAILED");
        }

        // Test balance after deposit
        account.deposit(500.0);
        if (account.getBalance() == 500.0) {
            System.out.println("testGetBalance after deposit: PASSED");
        } else {
            System.out.println("testGetBalance after deposit: FAILED");
        }
    }

    private static void testTransfer() {
        BankAccount account1 = new SavingsAccount();
        BankAccount account2 = new SavingsAccount();
        account1.deposit(500.0);

        // Test positive transfer
        try {
            account1.transfer(200.0, account2);
            if (account1.getBalance() == 300.0 && account2.getBalance() == 200.0) {
                System.out.println("testTransfer positive amount: PASSED");
            } else {
                System.out.println("testTransfer positive amount: FAILED");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("testTransfer positive amount: FAILED");
        }

        // Test negative transfer
        try {
            account1.transfer(-100.0, account2);
            System.out.println("testTransfer negative amount: FAILED");
        } catch (IllegalArgumentException e) {
            System.out.println("testTransfer negative amount: PASSED");
        } catch (InsufficientFundsException e) {
            System.out.println("testTransfer negative amount: FAILED");
        }

        // Test insufficient funds transfer
        try {
            account1.transfer(600.0, account2);
            System.out.println("testTransfer insufficient funds: FAILED");
        } catch (InsufficientFundsException e) {
            System.out.println("testTransfer insufficient funds: PASSED");
        }
    }
}
