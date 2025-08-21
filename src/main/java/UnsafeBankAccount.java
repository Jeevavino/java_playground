
public class UnsafeBankAccount {
    private double balance; // This instance variable is not thread-safe

    public UnsafeBankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // UNSAFE method - no synchronization
    public synchronized void unsafeDeposit(double amount) {
        double currentBalance = balance;
        // Simulate some processing time (race condition opportunity)
        try { Thread.sleep(10); } catch (InterruptedException e) {}
        balance = currentBalance + amount;
        System.out.println(Thread.currentThread().getName() +
                " deposited: " + amount +
                ", Balance: " + balance);
    }

    // UNSAFE method - no synchronization
    public synchronized void unsafeWithdraw(double amount) {
        if (balance >= amount) {
            double currentBalance = balance;
            // Simulate some processing time (race condition opportunity)
            try { Thread.sleep(10); } catch (InterruptedException e) {}
            balance = currentBalance - amount;
            System.out.println(Thread.currentThread().getName() +
                    " withdrew: " + amount +
                    ", Balance: " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}

 class ThreadUnsafeDemo {
    public static void main(String[] args) throws InterruptedException {
        UnsafeBankAccount account = new UnsafeBankAccount(1000.0);

        System.out.println("Initial Balance: " + account.getBalance());
        System.out.println("Starting UNSAFE transactions...");
        System.out.println("Expected final balance should be: 1000.0");
        System.out.println("But due to race conditions, it will be wrong!\n");

        // Create two threads that will cause race conditions
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.unsafeDeposit(100.0);
            }
        }, "Depositor-Thread");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.unsafeWithdraw(100.0);
            }
        }, "Withdrawer-Thread");

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to complete
        thread1.join();
        thread2.join();

        System.out.println("\nFinal Balance: " + account.getBalance());
        System.out.println("Expected Balance: 1000.0");
        System.out.println("Difference: " + (1000.0 - account.getBalance()));

    }


}


// Ultra-simple immutable class
record BankBalance(double amount) {
    BankBalance deposit(double add) {
        return new BankBalance(amount + add);
    }

    BankBalance withdraw(double subtract) {
        return new BankBalance(amount - subtract);
    }
}

// Simple thread demo
 class BankBalanceDemo {
    public static void main(String[] args) {
        BankBalance balance = new BankBalance(500);

        Thread t1 = new Thread(() -> {
            BankBalance newBalance = balance.deposit(100);
            System.out.println("T1 deposited: " + newBalance.amount());
        });

        Thread t2 = new Thread(() -> {
            BankBalance newBalance = balance.withdraw(200);
            System.out.println("T2 withdrew: " + newBalance.amount());
        });

        t1.start();
        t2.start();

        // Original balance remains unchanged - thread safe!
        System.out.println("Original balance: " + balance.amount());
    }
}
