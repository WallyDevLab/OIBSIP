package atmInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
//import java.util.Arrays;

public class ATMInterface {
//    private static final String String = null;
	private static double initialBalance = 100.0;

	private static Map<String, Double> userBalances = new HashMap<>();
    private static Map<String, StringBuilder> userTransactionHistory = new HashMap<>();
    private static ArrayList<String> userIds = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<Double> balances = new ArrayList<>();
	

    public static void main(String[] args) {
//        initializeUsers();
        try (Scanner scanner = new Scanner(System.in)){

	        System.out.println("Welcome to the ATM Interface!");
	        System.out.println(" ");
	        
	        System.out.print("Enter your user ID: ");
	        String userID = scanner.nextLine();
	        
	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        
	        userBalances.put(userID, initialBalance);
//	        System.out.println(authenticateUser(userID, password));
	        
	        while (authenticateUser(userID, password)) {
                showMenu(userID);
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                	case 1:
                		checkBalance(userID);  //This function works
                    break;
                    case 2:
                        viewTransactionHistory(userID);  //This function works
                        break;
                    case 3:
                    	Scanner withdrawInput = new Scanner(System.in);
                    	 System.out.print("Enter the amount to withdraw: ");
             	        double withdrawAmount = withdrawInput.nextDouble();
                        withdrawMoney(userID,withdrawAmount,initialBalance);
                        break;
                    case 4:
                    	Scanner depositInput = new Scanner(System.in);
                   	 	System.out.print("Enter the amount to deposit: ");
            	        double depositAmount = depositInput.nextDouble();
            	        
                        depositMoney(userID, depositAmount, initialBalance);
                        break;
                    case 5:
                    	Scanner transferInput = new Scanner(System.in);
            	        System.out.print("Enter the recipient's user ID: ");
            	        String recipientID = transferInput.nextLine();
                        transferMoney(userID, recipientID, initialBalance);
                        break;
                    case 6:  //This function works
                        System.out.println("Thank you for using the ATM Interface. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } 
        }
    }
        
	    private static boolean authenticateUser(String userID, String password) {
	        int index = userIds.indexOf(userID);
	
	        if (index != -1 && passwords.get(index).equals(password)) {
//	            System.out.println("Authentication successful! Welcome, " + userID + ".");
	        	int i;
	        } else {
	            System.out.println("Authentication failed. Signing up the user...");
	
	            // Validate password length
	            if (password.length() >= 6) {
	                System.out.println("Password should not be longer than 5 characters.");
	                return false;
	            }
	            // Sign up the user
	            signUpUser(userID, password, initialBalance);
	            System.out.println("User signed up successfully. Welcome, " + userID + ".");
	        }
			return true;
	    }
	
	    private static void signUpUser(String userId, String password, double initialBalance) {
	        userIds.add(userId);
	        passwords.add(password);
	        balances.add(initialBalance);
	    }
	
	
	    private static void showMenu(String userID) {
	        System.out.println("\nATM Menu for " + userID);
	        System.out.println("1. View Balance");
	        System.out.println("2. View Transaction History");
	        System.out.println("3. Withdraw Money");
	        System.out.println("4. Deposit Money");
	        System.out.println("5. Transfer Money");
	        System.out.println("6. Quit");
	        System.out.print("Enter your choice: ");
	    }
	
	    private static void viewTransactionHistory(String userID) {
	    	if (userTransactionHistory.isEmpty()) {
	    		System.out.println("You have no transaction history.");
	    	}
	    }
	    
	    
	    private static void checkBalance(String userId) {
	        int index = userIds.indexOf(userId);
	
	        if (index != -1) {
	            double balance = balances.get(index);
	            System.out.println("Balance for user " + userId + ": R" + balance);
	        } else {
	            System.out.println("User not found. Please authenticate first.");
	        }
	    }
	    
	    private static void withdrawMoney(String userID, Double withdrawAmount,Double initialBalance) {

	        if (userBalances.containsKey(userID)) {
	            if (withdrawAmount > 0 && withdrawAmount <= initialBalance) {
	                userBalances.put(userID, initialBalance - withdrawAmount);
	                
	                if (userTransactionHistory.containsKey(userID)) {
	                    userTransactionHistory.get(userID).append("Withdrawal: -" + withdrawAmount + "\n");
	                }
	                System.out.println("Withdrawal successful. Remaining balance: " + userBalances.get(userID));
	            } else {
	                System.out.println("Invalid amount or insufficient balance. Withdrawal failed.");
	            }
	        } else {
	            System.out.println("User's balance not found. Withdrawal failed.");
	        }
	    }
	    
	    private static void depositMoney(String userID, double depositAmount, Double initialBalance) {
	
	        if (userBalances.containsKey(userID)) {
	            if (depositAmount > 0) {
	                userBalances.put(userID, initialBalance + depositAmount );
	                if (userTransactionHistory.containsKey(userID)) {
	                    userTransactionHistory.get(userID).append("Deposit: +" + depositAmount  + "\n");
	                }
	                System.out.println("Deposit successful. New balance: " + userBalances.get(userID));
	            } else {
	                System.out.println("Invalid amount. Deposit failed.");
	            }
	        } else {
	            System.out.println("User's balance not found. Deposit failed.");
	        }
	    }
	
	    private static void transferMoney(String userID, String recipientID, double initialBalance) {
	        
	        if (userBalances.containsKey(recipientID)) {
	            if (initialBalance > 0 && initialBalance <= userBalances.get(userID)) {
	                userBalances.put(userID, userBalances.get(userID) - initialBalance);
	                userBalances.put(recipientID, userBalances.get(recipientID) + initialBalance);
	                userTransactionHistory.get(userID).append("Transfer to " + recipientID + ": -" + initialBalance + "\n");
	                userTransactionHistory.get(recipientID).append("Transfer from " + userID + ": +" + initialBalance + "\n");
	                System.out.println("Transfer successful. Remaining balance: " + userBalances.get(userID));
	            } else {
	                System.out.println("Invalid amount or insufficient balance. Transfer failed.");
	            }
	        } else {
	            System.out.println("Recipient's user ID not found. Transfer failed.");
	        }
	    }
}