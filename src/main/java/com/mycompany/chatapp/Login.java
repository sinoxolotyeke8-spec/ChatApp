/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

public class Login {
    
    // These store the user's details
    String username;
    String password;
    String phoneNumber;

    // This checks if the username contains an underscore and is at least 6 characters long
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() >= 5;
    }

    // This checks if the password is at least 8 characters, has a capital letter, a number, and a special character
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check each character in the password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            // If it is a capital letter
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            // If it is a number
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            // If it is a special character
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        // Return true if the password has all required parts
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    // This checks if the phone number starts with +27 and is no longer than 12 characters
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }

    // This method registers the user after checking username, password, and phone number
    public String registerUser(String username, String password, String phoneNumber) {
        // If the username is not valid
        if (!checkUserName(username)) {
            return "Username must contain an underscore and be at least 5 characters long.";
        }

        // If the password is not valid
        if (!checkPasswordComplexity(password)) {
            return "Password must be at least 8 characters long with a capital letter, number, and special character.";
        }

        // If the phone number is not valid
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Phone number must start with +27 and be no longer than 12 characters.";
        }

        // Save the valid data
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;

        // Return success message
        return "User registered successfully.";
    }

    // This method checks if the username and password match the stored ones
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // This method returns a message based on whether the login is successful or not
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + ", great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
    

    