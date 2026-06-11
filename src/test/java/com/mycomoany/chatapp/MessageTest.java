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
}