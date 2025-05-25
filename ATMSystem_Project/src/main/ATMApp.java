
package main;

import dao.AccountDAO;
import model.Account;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountDAO dao = new AccountDAO();

        System.out.println("==== Welcome to Simple ATM ====");
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Account acc = dao.findAccount(accNum, password);
        if (acc == null) {
            System.out.println("Invalid credentials!");
            return;
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: ₹" + acc.getBalance());
                    break;
                case 2:
                    System.out.print("Amount to deposit: ");
                    double deposit = sc.nextDouble();
                    acc.deposit(deposit);
                    dao.updateAccount(acc);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    if (acc.withdraw(withdraw)) {
                        dao.updateAccount(acc);
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using ATM.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
