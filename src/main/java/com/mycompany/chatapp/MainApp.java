package com.mycompany.chatapp;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login login = new Login();

        // =========================================================
        // USER REGISTRATION
        // =========================================================
        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter firstname: ");
        String firstname = input.nextLine();

        System.out.print("Enter lastname: ");
        String lastname = input.nextLine();

        String username;

        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            }

            System.out.println("Username must contain '_' and be no more than 5 characters.");
        }

        String password;

        while (true) {
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully.");
                break;
            }

            System.out.println("Password must contain 8 characters, capital letter, number and special character.");
        }

        String phone;

        while (true) {
            System.out.print("Enter phone number (+27xxxxxxxxx): ");
            phone = input.nextLine();

            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number captured successfully.");
                break;
            }

            System.out.println("Cell phone number incorrectly formatted.");
        }

        System.out.println(login.registerUser(username, password, phone));

        // =========================================================
        // LOGIN
        // =========================================================
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Username: ");
        String loginUsername = input.nextLine();

        System.out.print("Password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("Login failed.");
            input.close();
            return;
        }

        System.out.println("Welcome to QuickChat.");

        // Load stored messages
        Message.loadStoredMessages();

        ArrayList<Message> sentMessages = new ArrayList<>();

        boolean running = true;

        // =========================================================
        // MAIN MENU
        // =========================================================
        while (running) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose: ");

            int choice;

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (choice) {

                // =====================================================
                // SEND MESSAGES
                // =====================================================
                case 1:

                    System.out.print("How many messages: ");

                    int numMessages;

                    try {
                        numMessages = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        break;
                    }

                    for (int i = 0; i < numMessages; i++) {

                        System.out.println("\nMessage " + (i + 1));

                        System.out.print("Recipient: ");
                        String recipient = input.nextLine();

                        System.out.print("Message: ");
                        String text = input.nextLine();

                        Message msg = new Message(i + 1, recipient, text);

                        if (!msg.checkRecipientCell()) {
                            System.out.println("Cell phone number incorrectly formatted.");
                            continue;
                        }

                        System.out.println(msg.checkMessageLength());

                        if (!msg.checkMessageLength().equals("Message ready to send.")) {
                            continue;
                        }

                        System.out.println("\n1. Send Message");
                        System.out.println("2. Delete Message");
                        System.out.println("3. Store Message");
                        System.out.print("Choose option: ");

                        int option;

                        try {
                            option = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid option.");
                            continue;
                        }

                        System.out.println(msg.sendMessage(option));

                        if (option == 1) {
                            sentMessages.add(msg);

                            System.out.println("Message ID Valid: "
                                    + msg.checkMessageID());

                            msg.printMessages();
                        }
                    }
                    break;

                // =====================================================
                // SHOW SENT MESSAGES
                // =====================================================
                case 2:

                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages sent.");
                    } else {

                        System.out.println("\n=== SENT MESSAGES ===");

                        for (Message msg : sentMessages) {
                            msg.printMessages();
                            System.out.println();
                        }

                        System.out.println("Total Messages: "
                                + Message.returnTotalMessages());
                    }
                    break;

                // =====================================================
                // QUIT
                // =====================================================
                case 3:

                    System.out.println("Logging out...");
                    running = false;
                    break;

                // =====================================================
                // STORED MESSAGES MENU
                // =====================================================
                case 4:

                    storedMessagesMenu(input);
                    break;

                default:

                    System.out.println("Invalid option.");
            }
        }

        input.close();
    }

    // =========================================================
    // STORED MESSAGES SUB MENU
    // =========================================================
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

            String choice = input.nextLine().trim().toLowerCase();

            switch (choice) {

                case "a":
                    System.out.println(Message.printReport());
                    break;

                case "b":
                    String longest = Message.displayLongestMessage();

                    if (longest.isEmpty()) {
                        System.out.println("No stored messages found.");
                    } else {
                        System.out.println("Longest Message:");
                        System.out.println(longest);
                    }
                    break;

                case "c":
                    System.out.print("Enter Message ID: ");
                    String id = input.nextLine();

                    System.out.println(
                            Message.searchByMessageID(id));
                    break;

                case "d":
                    System.out.print("Enter Recipient: ");
                    String recipient = input.nextLine();

                    String result =
                            Message.searchByRecipient(recipient);

                    if (result.isEmpty()) {
                        System.out.println("No messages found.");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case "e":
                    System.out.print("Enter Message Hash: ");
                    String hash = input.nextLine();

                    System.out.println(
                            Message.deleteByHash(hash));
                    break;

                case "f":
                    System.out.println(Message.printReport());
                    break;

                case "q":
                    inSubMenu = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}