/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycomoany.chatapp;

import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    // =========================================================
    // CREATE TEST OBJECTS BEFORE EACH TEST
    // =========================================================

    @BeforeEach
    public void setUp() {

        // Valid message object
        message1 = new Message(
                1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // Invalid recipient number object
        message2 = new Message(
                2,
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );
    }

    // =========================================================
    // MESSAGE LENGTH TESTS
    // =========================================================

    @Test
    public void testCheckMessageLength_ValidMessage() {

        // FIX: now calls no-arg version which uses this.messageText
        String result = message1.checkMessageLength();

        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testCheckMessageLength_MessageTooLong() {

        String longMessage = "A".repeat(260);

        Message longMsg = new Message(
                3,
                "+27718693002",
                longMessage
        );

        // FIX: no-arg call using internal messageText
        String result = longMsg.checkMessageLength();

        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    // =========================================================
    // RECIPIENT NUMBER TESTS
    // =========================================================

    @Test
    public void testCheckRecipientCell_ValidNumber() {

        // FIX: no-arg call using internal recipient
        assertTrue(message1.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_InvalidNumber() {

        // FIX: no-arg call using internal recipient
        assertFalse(message2.checkRecipientCell());
    }

    // =========================================================
    // MESSAGE HASH TESTS
    // =========================================================

    @Test
    public void testCreateMessageHash_CorrectFormat() {

        message1.setMessageID("1234567890");
        String hash = message1.createMessageHash();

        // Hash should end with messageNumber:FIRSTWORDLASTWORD
        assertTrue(hash.endsWith(":1:HITONIGHT"));
    }

    @Test
    public void testCreateMessageHash_Uppercase() {

        message1.setMessageID("1234567890");
        String hash = message1.createMessageHash();

        assertEquals(hash.toUpperCase(), hash);
    }

    // =========================================================
    // MESSAGE ID TESTS
    // =========================================================

    @Test
    public void testMessageID_NotNull() {
        assertNotNull(message1.getMessageID());
    }

    @Test
    public void testMessageID_LengthIs10() {
        assertEquals(10, message1.getMessageID().length());
    }

    // =========================================================
    // SEND MESSAGE TESTS
    // =========================================================

    @Test
    public void testSendMessage_SendOption() {

        // FIX: renamed from sentMessage -> sendMessage
        String result = message1.sendMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSendMessage_DiscardOption() {

        String result = message1.sendMessage(2);
        assertEquals("Message discarded.", result);
    }

    @Test
    public void testSendMessage_StoreOption() {

        String result = message1.sendMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    // =========================================================
    // TOTAL MESSAGE TEST
    // =========================================================

    @Test
    public void testReturnTotalMessages() {

        int total = Message.returnTotalMessages();
        assertTrue(total > 0);
    }
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sendMessage(1);

        Message msg4 = new Message(4, "0838884567", "It is dinner time!");
        msg4.sendMessage(1);

        // Search by message text via recipient to confirm both are in the arrays
        String result1 = Message.searchByRecipient("+27834557896");
        String result4 = Message.searchByRecipient("0838884567");

        assertTrue(result1.contains("Did you get the cake?"));
        assertTrue(result4.contains("It is dinner time!"));
    }
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {
        // Store message 2 (the longest) using option 3
        Message msg2 = new Message(2, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        msg2.sendMessage(3);

        // Store a shorter message so there is something to compare against
        Message msg3 = new Message(3, "+27834484567", "OK noted.");
        msg3.sendMessage(3);

        // FIX: renamed from sentMessage -> sendMessage
        String result = message1.sendMessage(1);
        assertEquals("Message successfully sent.", result);
        String longest = Message.displayLongestMessage();

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest
        );
    }
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {
        Message msg4 = new Message(4, "0838884567", "It is dinner time!");
        msg4.sendMessage(1);

        String id = msg4.getMessageID();
        String result = Message.searchByMessageID(id);

        //String result = message1.sendMessage(2);
        assertEquals("Message discarded.", result);
        assertEquals("It is dinner time!", result);
    }
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {
        Message msg2 = new Message(2, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        msg2.sendMessage(1);

        String result = message1.sendMessage(3);
        assertEquals("Message successfully stored.", result);
        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        msg5.sendMessage(1);

        //String result = Message.searchByRecipient("+27838884567");

        assertTrue(result.contains(
                "Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    @Test
    public void testDeleteByHash_removesCorrectMessage() {
        Message msg2 = new Message(2, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        msg2.sendMessage(1);

        String hash = msg2.getMessageHash();
        String result = Message.deleteByHash(hash);

        assertEquals(
                "Message: Where are you? You are late! I have asked you to be on time."
                + " successfully deleted.",
                result
        );
    }
    @Test
    //public void testReturnTotalMessages() {
    public void testDisplayReport_containsRequiredFields() {
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sendMessage(1);

        String report = Message.printReport();

        int total = Message.returnTotalMessages();
        assertTrue(total > 0);
        assertTrue(report.contains(msg1.getMessageHash()));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}