/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // --- REGISTRATION SECTION ---
        System.out.println("=== USER REGISTRATION ===");

        System.out.println("Enter your firstname:");
        String firstname = input.nextLine();

        System.out.println("Enter your lastname:");
        String lastname = input.nextLine();

        // Username
        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = input.nextLine();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Invalid username. Ensure that it contains <=5 characters and '_'.");
            }
        }

        // Password
        String password;
        while (true) {
            System.out.print("Enter a password: ");
            password = input.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully");
                break;
            } else {
                System.out.println("Password must be eight characters long, including a special character, capital letter, and number.");
            }
        }

        // Phone number
        String phone;
        while (true) {
            System.out.print("Enter International code: ");
            phone = input.nextLine();
            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("CellPhone number captured successfully.");
                break;
            } else {
                System.out.println("Invalid CellPhone number, does not contain international code");
            }
        }

        // Register user
        System.out.println(login.registerUser(username, password, phone));

        // --- LOGIN SECTION ---
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));
        
         // --- CHAT SECTION ---
        if (loggedIn) {
            System.out.println("\n=== WELCOME TO CHATAPP ===");

            // Store sent messages as "recipient: message" strings
            ArrayList<String> sentMessages = new ArrayList<>();
            boolean running = true;

            while (running) {
                System.out.println("\n===== CHAT MENU =====");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");

                String choiceInput = input.nextLine();
                int choice;

                // FIX: safely parse menu choice to avoid InputMismatchException crash
                try {
                    choice = Integer.parseInt(choiceInput.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("How many messages: ");
                        String numInput = input.nextLine();
                        int numMessages;

                        // FIX: safely parse number of messages
                        try {
                            numMessages = Integer.parseInt(numInput.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Returning to menu.");
                            break;
                        }

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\nMessage " + (i + 1));

                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter your message: ");
                            String message = input.nextLine();

                            // Store message for later display
                            sentMessages.add("To " + recipient + ": " + message);
                            System.out.println("Message sent successfully.");
                        }
                        break;

                    case 2:
                        // FIX: actually display stored messages instead of "Coming soon"
                        if (sentMessages.isEmpty()) {
                            System.out.println("No messages sent yet.");
                        } else {
                            System.out.println("\n--- Recently Sent Messages ---");
                            for (String msg : sentMessages) {
                                System.out.println(msg);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Logging out...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

        } else {
            System.out.println("Login failed. Exiting ChatApp.");
        }

        input.close();
    }
}

