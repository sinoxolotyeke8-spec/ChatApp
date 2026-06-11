# ChatApp Part 1

## Student Information

**Student Name:** Sinoxolo Tyeke
**Student Number:** ST10508969
**Module:** PROG5121 – Programming 1A

---


# Project Overview

ChatApp Part 1 is a Java-based messaging application that allows users to:

* Register an account
* Log in using registered credentials
* Send messages
* View recently sent messages
* Store messages in a JSON file
* Generate unique message IDs
* Generate message hashes
* Validate recipient cellphone numbers
* Validate message length
* Perform automated testing using JUnit 5

The application demonstrates Object-Oriented Programming (OOP) principles, input validation, file handling, arrays/lists, and unit testing.

---

# Features

## User Registration

The application allows users to register by entering:

* First Name
* Last Name
* Username
* Password
* International Phone Number

### Username Rules

The username must:

* Contain an underscore (_)
* Be 5 characters or fewer

Example:

user_

### Password Rules

The password must contain:

* At least 8 characters
* One uppercase letter
* One number
* One special character

Example:

Password@1

### Phone Number Rules

The cellphone number must contain a valid South African international code.

Example:

+27718693002

---

# User Login

After registration, the user can log in using:

* Username
* Password

The system validates the credentials and grants access to the chat menu.

---

# Chat Menu

After successful login, the following options are available:

### Option 1: Send Messages

Users can:

* Enter the number of messages to send
* Enter recipient cellphone numbers
* Enter message content

Messages are stored in memory and displayed later.

### Option 2: Show Recently Sent Messages

Displays all messages sent during the session.

Example:

To +27718693002: Hello Mike

To +27831234567: Meeting starts at 2 PM

### Option 3: Quit

Logs the user out and closes the application.

---

# Message Class Functionality

The Message class is responsible for handling all message-related operations.

## Message ID Generation

Each message receives a unique message ID.

Example:

1234567890

### Validation

The message ID must:

* Not be null
* Contain exactly 10 digits

---

## Message Length Validation

Maximum allowed length:

250 characters

### Valid Message

Message ready to send.

### Invalid Message

Message exceeds 250 characters by X, please reduce size.

---

## Recipient Validation

Recipient numbers must follow this format:

+27XXXXXXXXX

### Valid Example

+27718693002

### Invalid Example

08575975889

---

## Message Hash Generation

A unique hash is generated using:

* First 2 digits of Message ID
* Message Number
* First Word of Message
* Last Word of Message

Example:

12:1:HITONIGHT

---

## Message Storage

Messages can be stored in a JSON file called:

storedMessages.json

Stored information includes:

* Message ID
* Message Hash
* Recipient Number
* Message Text

---

# Technologies Used

* Java
* NetBeans IDE
* JUnit 5
* JSON File Storage
* ArrayList
* FileWriter
* Scanner Class

---

# Project Structure

com.mycompany.chatapppart_1

├── MainApp.java

├── Login.java

├── Message.java

└── MessageTest.java

---

# Testing

JUnit 5 was used to test the application's functionality.

## Tests Included

### Message Length Tests

* Valid message length
* Message exceeding 250 characters

### Recipient Validation Tests

* Valid recipient number
* Invalid recipient number

### Message Hash Tests

* Hash format validation
* Uppercase hash validation

### Message ID Tests

* ID not null
* ID length equals 10

### Send Message Tests

* Send message
* Discard message
* Store message

### Total Messages Test

* Verify message counter increases correctly

---

# How to Run the Program

1. Open the project in NetBeans.
2. Build the project.
3. Run MainApp.java.
4. Complete registration.
5. Log in using your credentials.
6. Access the chat menu.
7. Send messages or view previously sent messages.

---

# Example Execution

=== USER REGISTRATION ===

Enter your firstname:
Sinoxolo

Enter your lastname:
Tyeke

Enter a username:
user_

Username successfully captured.

Enter a password:
Password@1

Password captured successfully.

Enter International code:
+27718693002

CellPhone number captured successfully.

=== USER LOGIN ===

Enter your username:
user_

Enter your password:
Password@1

Welcome to ChatApp

===== CHAT MENU =====

1. Send Messages

2. Show recently sent messages

3. Quit

Choose an option:

---

# OOP Concepts Demonstrated

## Encapsulation

Private variables are used inside the Message class and accessed through getters and setters.

## Constructors

Constructors initialize Message objects with message information.

## Methods

Methods perform validation, hashing, storage, and message management.

## Objects

Message objects are created and manipulated throughout the application.

---

# Future Improvements

* GUI implementation using JavaFX
* Database integration using MySQL
* Message deletion feature
* Search functionality
* User profile management
* Message encryption
* Real-time messaging

---

# Author

**Sinoxolo Tyeke**
**Student Number:** ST10508969

PROG5121 Programming 1A Project
