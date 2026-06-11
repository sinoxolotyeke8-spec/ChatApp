package com.mycompany.chatapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONObject;

public class Message {

    // Instance Variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static Counter
    private static int totalMessages = 0;

    // Parallel Arrays
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> storedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();
    private static ArrayList<String> recipients = new ArrayList<>();

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        totalMessages++;
    }

    // Generate Random 10-Digit Message ID
    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Validate Message ID
    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    // Check Message Length
    public String checkMessageLength(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Message cannot be empty.";
        }

        if (text.length() <= 250) {
            return "Message ready to send.";
        }

        int exceeded = text.length() - 250;
        return "Message exceeds 250 characters by " + exceeded + " characters.";
    }

    // Overloaded Version
    public String checkMessageLength() {
        return checkMessageLength(this.messageText);
    }

    // Validate Recipient Number
    public String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

    // Boolean Version
    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("\\+27\\d{9}");
    }

    // Create Message Hash
    public String createMessageHash() {

        if (messageText == null || messageText.trim().isEmpty()) {
            return "INVALID";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].toUpperCase();

        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    // Send / Store / Discard Message
    public String sendMessage(int option) {

        switch (option) {

            case 1:
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipient);
                return "Message successfully sent.";

            case 2:
                disregardedMessages.add(messageText);
                return "Message discarded.";

            case 3:
                storeMessage();
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipient);
                return "Message successfully stored.";

            default:
                return "Invalid option selected.";
        }
    }

    // Store Message to JSON File
    public void storeMessage() {

        String jsonEntry =
                "{"
                + "\"MessageID\":\"" + messageID + "\","
                + "\"MessageHash\":\"" + messageHash + "\","
                + "\"Recipient\":\"" + recipient + "\","
                + "\"Message\":\"" + messageText.replace("\"", "\\\"") + "\""
                + "}";

        try (FileWriter file = new FileWriter("messages.json", true)) {

            file.write(jsonEntry);
            file.write(System.lineSeparator());

            System.out.println("Message stored successfully.");

        } catch (IOException e) {

            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Load Stored Messages
    public static void loadStoredMessages() {

        storedMessages.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                JSONObject obj = new JSONObject(line);
                storedMessages.add(obj.getString("Message"));
            }

        } catch (Exception e) {

            System.out.println("No stored messages found.");
        }
    }

    // Display Longest Message
    public static String displayLongestMessage() {

        String longest = "";

        for (String msg : storedMessages) {

            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        return longest;
    }

    // Search By Message ID
    public static String searchByMessageID(String id) {

        for (int i = 0; i < messageIDs.size(); i++) {

            if (messageIDs.get(i).equals(id)) {
                return sentMessages.get(i);
            }
        }

        return "Message not found.";
    }

    // Search By Recipient
    public static String searchByRecipient(String recipientNumber) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < recipients.size(); i++) {

            if (recipients.get(i).equals(recipientNumber)) {
                result.append(sentMessages.get(i)).append("\n");
            }
        }

        return result.toString();
    }

    // Delete By Hash
    public static String deleteByHash(String hash) {

        for (int i = 0; i < messageHashes.size(); i++) {

            if (messageHashes.get(i).equals(hash)) {

                String deleted = sentMessages.get(i);

                sentMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);

                return "Message: " + deleted + " successfully deleted.";
            }
        }

        return "Hash not found.";
    }

    // Print Report
    public static String printReport() {

        StringBuilder report = new StringBuilder();

        report.append("=== MESSAGE REPORT ===\n");

        for (int i = 0; i < sentMessages.size(); i++) {

            report.append("Hash: ").append(messageHashes.get(i)).append("\n");
            report.append("Recipient: ").append(recipients.get(i)).append("\n");
            report.append("Message: ").append(sentMessages.get(i)).append("\n");
            report.append("-------------------------\n");
        }

        return report.toString();
    }

    // Print Message Details
    public void printMessages() {

        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    // Total Messages
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Clear All Arrays
    public static void clearAll() {

        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipients.clear();

        totalMessages = 0;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    // Setters
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
}