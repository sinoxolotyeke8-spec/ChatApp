/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

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
    }
}

