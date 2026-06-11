/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    // Unique ID assigned to each message
    private String messageID;

    // Number representing the order of the message
    private int messageNumber;

    // Stores recipient cellphone number
    private String recipient;

    // Stores actual message text
    private String messageText;

    // Stores generated hash value
    private String messageHash;

    // Counter used to track total messages created
    private static int totalMessages = 0;
        // =========================================================
    // CONSTRUCTOR
    // PARALLEL ARRAYS - populated as messages are processed
    // =========================================================
    // Stores text of every message the user chose to Send (option 1)
    private static ArrayList<String> sentMessages = new ArrayList<>();

    // Stores text of every message the user chose to Discard (option 2)
    private static ArrayList<String> disregardedMessages = new ArrayList<>();

    // Stores text of messages read back from the JSON file (loadStoredMessages only)
    private static ArrayList<String> storedMessages = new ArrayList<>();

    // Stores the hash for every message that was sent or stored
    private static ArrayList<String> messageHashes = new ArrayList<>();

    // Stores the ID for every message that was sent or stored
    private static ArrayList<String> messageIDs = new ArrayList<>();

    // Stores the recipient for every message that was sent or stored
    private static ArrayList<String> recipients = new ArrayList<>();
    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        totalMessages++;
    }

    // =========================================================
    // GENERATE RANDOM 10-DIGIT MESSAGE ID
    // =========================================================

    public String generateMessageID() {
        Random random = new Random();
        long number = 1L + (long) (random.nextDouble() * 9L);
        return String.valueOf(number);
    }

    // =========================================================
    // VALIDATE MESSAGE ID
    // =========================================================

    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    // =========================================================
    // CHECK MESSAGE LENGTH - WITH ARGUMENT (internal use)
    // =========================================================

    public String checkMessageLength(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Message cannot be empty.";
        }
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceeded = text.length() - 250;
            return "Message exceeds 250 characters by " + exceeded + ", please reduce size.";
        }
    }

    // =========================================================
    // CHECK MESSAGE LENGTH - NO ARGUMENT (used by tests)
    // =========================================================

    public String checkMessageLength() {
        return checkMessageLength(this.messageText);
    }

    // =========================================================
    // VALIDATE RECIPIENT NUMBER - WITH ARGUMENT (internal use)
    // =========================================================

    public String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    // =========================================================
    // VALIDATE RECIPIENT NUMBER - NO ARGUMENT (used by tests)
    // =========================================================

    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("\\+27\\d{9}");
    }

    // =========================================================
    // GENERATE MESSAGE HASH
    // =========================================================

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "INVALID";
        }

        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase();

        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // =========================================================
    // HANDLE MESSAGE OPTIONS (used by tests)
    // =========================================================

    public String sendMessage(int option) {
        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Message discarded.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }

    // =========================================================
    // SAVE MESSAGE TO FILE
    // =========================================================

    public void storeMessage() {
        String jsonEntry = "{"
                + "\"MessageID\":\"" + messageID + "\","
                + "\"MessageHash\":\"" + messageHash + "\","
                + "\"Recipient\":\"" + recipient + "\","
                + "\"Message\":\"" + messageText.replace("\"", "\\\"") + "\""
                + "}";

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(jsonEntry);
            file.write(System.lineSeparator());
            System.out.println("Message stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // =========================================================
    // PRINT MESSAGE DETAILS
    // =========================================================

    public void printMessages() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    // =========================================================
    // RETURN TOTAL MESSAGE COUNT
    // =========================================================

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public String getMessageID()     { return messageID; }
    public int getMessageNumber()    { return messageNumber; }
    public String getRecipient()     { return recipient; }
    public String getMessageText()   { return messageText; }
    public String getMessageHash()   { return messageHash; }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setMessageID(String messageID)     { this.messageID = messageID; }
    public void setRecipient(String recipient)     { this.recipient = recipient; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
}
