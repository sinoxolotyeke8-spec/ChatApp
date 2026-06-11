/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycomoany.chatapp;

import com.mycompany.chatapp.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {

    Login login = new Login();

    // USERNAME TESTS
    @Test 
    public void testValidUsername() {
        // Username should contain an underscore and be at least 5 characters long to be valid
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        // The username will be invalid if there is no underscore    
        assertFalse(login.checkUserName("kyle1"));
    }

    @Test
    public void testInvalidUsername_TooShort() {
        // The username must contain at least 5 characters to be valid (including underscore)
        assertFalse(login.checkUserName("ky_1"));
    }

    @Test
    public void testInvalid_Username_TooLong() {
        // The username must not exceed 5 characters before the underscore or after (this test is now adjusted for at least 5 characters)
        assertTrue(login.checkUserName("kyle_126"));
    }

    // PASSWORD TESTS
    @Test
    public void testValidPassword() {
        // Check if the password meets requirements (at least 8 chars, capital letter, special char, number)
        assertTrue(login.checkPasswordComplexity("Ch@ttApp1"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        // Ensure the password is at least 8 characters in length    
        assertFalse(login.checkPasswordComplexity("Ch@1"));
    }    

    @Test
    public void testInvalidPassword_NoCapitalLetterOrSpecialCharacter() {
        // The password does not have a special character or capital letter   
        assertFalse(login.checkPasswordComplexity("chattapp1"));
    }

    // CELLPHONE NUMBER TESTS
    @Test
    public void testValidCellPhoneNumber() {
        // Ensure the cellphone number contains a South African international code (+27)
        assertTrue(login.checkCellPhoneNumber("+27655922556"));
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        // Cellphone number must contain South African international code (+27)
        assertFalse(login.checkCellPhoneNumber("0655922556"));
    }

    // USER LOGIN TESTS
    @Test
    public void testLoginWithIncorrectDetails() {
        // Register a user first
        login.registerUser("kyl_1", "Ch@ttApp1", "+27655922556");

        // Try to login with incorrect details (incorrect username and password)
        assertFalse(login.loginUser("kyle_1", "wrongpassword"));
    }

    @Test
    public void testValidLogin() {
        // Register a user first
        login.registerUser("kyl_1", "Ch@ttApp1", "+27655922556");

        // Test with the correct login details
        assertTrue(login.loginUser("kyl_1", "Ch@ttApp1"));
    }
}