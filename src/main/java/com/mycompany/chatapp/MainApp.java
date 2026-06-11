/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.ArrayList;
import java.util.Scanner;


public class MainApp {

    public static void main(String[] args) {

        // Scanner for reading user input from console
        Scanner input = new Scanner(System.in);

        // Create a Login object to handle registration and authentication
        Login login = new Login();

        // --- REGISTRATION SECTION ---
        // =========================================================
        // USER REGISTRATION
        // =========================================================
        System.out.println("=== USER REGISTRATION ===");

        System.out.println("Enter your firstname:");
        System.out.print("Enter firstname: ");
        String firstname = input.nextLine();

        System.out.println("Enter your lastname:");
        System.out.print("Enter lastname: ");
        String lastname = input.nextLine();

        // Username
        String username;

        // Prompt until a valid username is entered
        while (true) {
            System.out.print("Enter a username: ");
            System.out.print("Enter username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Invalid username. Ensure that it contains <=5 characters and '_'.");
            }

            System.out.println(
                    "Username must contain '_' and be no more than 5 characters."
            );
        }

        // Password
        String password;

        // Prompt until a valid password is entered
        while (true) {
            System.out.print("Enter a password: ");
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully");
                System.out.println("Password captured successfully.");
                break;
            } else {
                System.out.println("Password must be eight characters long, including a special character, capital letter, and number.");
            }

            System.out.println(
                    "Password must contain 8 characters, capital letter, number and special character."
            );
        }

        // Phone number
        String phone;

        // Prompt until a valid phone number is entered
        while (true) {
            System.out.print("Enter International code: ");
            System.out.print("Enter phone number: ");
            phone = input.nextLine();

            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("CellPhone number captured successfully.");
                System.out.println("Cell phone number captured successfully.");
                break;
            } else {
                System.out.println("Invalid CellPhone number, does not contain international code");
            }

            System.out.println("Cell phone number incorrectly formatted.");
        }

        // Register user
        // Register the user
        System.out.println(login.registerUser(username, password, phone));

        // --- LOGIN SECTION ---
        // =========================================================
        // USER LOGIN
        // =========================================================
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter your username: ");
        System.out.print("Username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password: ");
        System.out.print("Password: ");
        String loginPassword = input.nextLine();

        // Attempt login
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        // Show login status message
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
        // If login failed, exit the program
        if (!loggedIn) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Welcome to QuickChat.");

        // =========================================================
        // PART 3 - Load stored messages from JSON into array at startup
        // =========================================================
        Message.loadStoredMessages();

        // List to store sent Message objects for this session
        ArrayList<Message> sentMessages = new ArrayList<>();

        boolean running = true;

        // =========================================================
        // MAIN CHAT MENU LOOP
        // =========================================================
        while (running) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");   // Part 3
            System.out.print("Choose: ");

            int choice;

            // Safely parse user menu input
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (choice) {

                // =========================================================
                // SEND MESSAGES
                // =========================================================
                case 1:
                    System.out.print("How many messages: ");

                    int numMessages;

                    // Safely parse number of messages to send
                    try {
                        numMessages = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        break;
                    }

                    // Loop for each message
                    for (int i = 0; i < numMessages; i++) {

                        System.out.println("\nMessage " + (i + 1));

                        // Get recipient and message text
                        System.out.print("Recipient: ");
                        String recipient = input.nextLine();

                        System.out.print("Message: ");
                        String text = input.nextLine();

                        // Create a new Message object
                        Message msg = new Message(i + 1, recipient, text);

                        // Validate recipient format
                        if (!msg.checkRecipientCell()) {
                            System.out.println("Cell phone number incorrectly formatted.");
                            continue;
                        }

                        // Check message length
                        System.out.println(msg.checkMessageLength());
                        if (!msg.checkMessageLength().equals("Message ready to send.")) {
                            continue;
                        }

                        // Message send options
                        System.out.println("\n1. Send Message");
                        System.out.println("2. Delete Message");
                        System.out.println("3. Store Message");

                        int option;

                        // Safely parse send option
                        try {
                            numMessages = Integer.parseInt(numInput.trim());
                            option = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Returning to menu.");
                            break;
                            System.out.println("Invalid option.");
                            continue;
                        }

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\nMessage " + (i + 1));
                        // Execute chosen message action
                        System.out.println(msg.sendMessage(option));

                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();
                        // If message was sent, add to local list and show details
                        if (option == 1) {
                            sentMessages.add(msg);
                            System.out.println("Message ID Valid: " + msg.checkMessageID());
                            msg.printMessages();
                        }
                    }
                    break;

                            System.out.print("Enter your message: ");
                            String message = input.nextLine();
                // =========================================================
                // SHOW SENT MESSAGES
                // =========================================================
                case 2:

                            // Store message for later display
                            sentMessages.add("To " + recipient + ": " + message);
                            System.out.println("Message sent successfully.");
                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages sent.");
                    } else {
                        for (Message msg : sentMessages) {
                            msg.printMessages();
                            System.out.println();
                        }
                        break;
                        System.out.println("Total Messages: " + Message.returnTotalMessages());
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
                // =========================================================
                // QUIT CHAT APP
                // =========================================================
                case 3:
                    System.out.println("Logging out...");
                    running = false;
                    break;

                    case 3:
                        System.out.println("Logging out...");
                        running = false;
                        break;
                // =========================================================
                // PART 3 - STORED MESSAGES SUB-MENU
                // =========================================================
                case 4:
                    storedMessagesMenu(input);
                    break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
                default:
                    System.out.println("Invalid option.");
            }

        } else {
            System.out.println("Login failed. Exiting ChatApp.");
        }

        // Close scanner to prevent resource leak
        input.close();
    }

    // =========================================================
    // STORED MESSAGES SUB-MENU (Part 3)
    // =========================================================
    /**
     * Displays the stored messages sub-menu and handles all six options:
     * display all stored messages, longest message, search by ID,
     * search by recipient, delete by hash, and full report.
     *
     * @param input the shared Scanner instance for reading user input
     */
    private static void storedMessagesMenu(Scanner input) {

        boolean inSubMenu = true;

        while (inSubMenu) {

            System.out.println("\n===== STORED MESSAGES =====");
            System.out.println("a. Display all stored messages");
            System.out.println("b. Display longest message");
            System.out.println("c. Search by message ID");
            System.out.println("d. Search by recipient");
            System.out.println("e. Delete by message hash");
            System.out.println("f. Display full report");
            System.out.println("q. Return to main menu");
            System.out.print("Choose: ");

            String subChoice = input.nextLine().trim().toLowerCase();

            switch (subChoice) {

                // Display all stored messages
                case "a":
                    String longest = Message.displayLongestMessage();
                    if (longest.isEmpty()) {
                        System.out.println("No stored messages found.");
                    } else {
                        System.out.println(Message.printReport());
                    }
                    break;

                // Display the longest stored message
                case "b":
                    String longestMsg = Message.displayLongestMessage();
                    if (longestMsg.isEmpty()) {
                        System.out.println("No stored messages found.");
                    } else {
                        System.out.println("Longest message: " + longestMsg);
                    }
                    break;

                // Search by message ID
                case "c":
                    System.out.print("Enter message ID: ");
                    String searchID = input.nextLine().trim();
                    System.out.println(Message.searchByMessageID(searchID));
                    break;

                // Search by recipient number
                case "d":
                    System.out.print("Enter recipient number: ");
                    String searchRecipient = input.nextLine().trim();
                    String recipientResults = Message.searchByRecipient(searchRecipient);
                    if (recipientResults.isEmpty()) {
                        System.out.println("No messages found for that recipient.");
                    } else {
                        System.out.println(recipientResults);
                    }
                    break;

                // Delete a message by its hash
                case "e":
                    System.out.print("Enter message hash: ");
                    String hash = input.nextLine().trim();
                    System.out.println(Message.deleteByHash(hash));
                    break;

                // Display full report of all sent messages
                case "f":
                    System.out.println(Message.printReport());
                    break;

                // Return to main menu
                case "q":
                    inSubMenu = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a, b, c, d, e, f, or q.");
            }
        }
    }
}