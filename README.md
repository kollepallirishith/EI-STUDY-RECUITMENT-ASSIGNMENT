# README for Design Patterns Exercises

## Table of Contents

1. [Exercise 1: Problem Statement on Design Patterns](#exercise-1-problem-statement-on-design-patterns)
   - [Overview](#overview)
   - [Use Cases](#use-cases)
     - [Behavioral Design Patterns](#behavioral-design-patterns)
     - [Creational Design Patterns](#creational-design-patterns)
     - [Structural Design Patterns](#structural-design-patterns)
2. [Exercise 2: Problem Statements for Mini-Projects](#exercise-2-problem-statements-for-mini-projects)
   - [Overview](#overview-1)
   - [Problem Statements](#problem-statements)
   - [ChatRoomV1 Mini-Project](#chatroomv1-mini-project)

---

## Exercise 1: Problem Statement on Design Patterns

### Overview
In this exercise, you are tasked with demonstrating your understanding of various software design patterns through creative use cases. You will create six different use cases, divided into three categories: behavioral, creational, and structural design patterns.

### Use Cases

#### Behavioral Design Patterns
1. **Game Event Manager (Observer Pattern)**
   - **Definition**: The Observer Pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
   - **Use Case**: Create a system where various game components (like players, enemies, and UI elements) can react to in-game events (e.g., score changes, level ups) through a subscription mechanism.

2. **Sorting Strategy (Strategy Pattern)**
   - **Definition**: The Strategy Pattern enables selecting an algorithm's behavior at runtime. It defines a family of algorithms, encapsulates each one, and makes them interchangeable.
   - **Use Case**: Implement a sorting algorithm interface with multiple concrete strategies (like QuickSort, MergeSort, and BubbleSort) that can be selected at runtime based on user input.

#### Creational Design Patterns
3. **Animal Factory (Factory Pattern)**
   - **Definition**: The Factory Pattern provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created.
   - **Use Case**: Design a factory that creates different animal objects (like Dog, Cat, Bird) based on input parameters, demonstrating polymorphism and encapsulation.

4. **Undo Manager (Singleton Pattern)**
   - **Definition**: The Singleton Pattern ensures a class has only one instance and provides a global point of access to it.
   - **Use Case**: Implement a singleton class that tracks user actions and provides an undo functionality, ensuring that only one instance of the Undo Manager exists throughout the application.

#### Structural Design Patterns
5. **Temperature Converter (Adapter Pattern)**
   - **Definition**: The Adapter Pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by converting the interface of one class into an interface expected by the clients.
   - **Use Case**: Develop an adapter that converts temperature measurements between Celsius and Fahrenheit, allowing clients to interact with the conversion seamlessly.

6. **Home Theater System (Facade Pattern)**
   - **Definition**: The Facade Pattern provides a simplified interface to a complex subsystem, making it easier for clients to interact with the subsystem without dealing with its complexities.
   - **Use Case**: Create a facade that simplifies the operation of a complex home theater system, providing a unified interface to manage audio, video, and lighting components.

---

## Exercise 2: Problem Statements for Mini-Projects

### Overview
In this exercise, you are required to build a simple console/terminal-based application. The focus should be on implementing solid logic and code quality, while adhering to design principles and patterns.
### ChatRoomV1 Mini-Project
As part of your chosen project, you may implement **ChatRoomV1**. This project will include a chat server and client system that enables real-time communication. Here are the primary features and considerations:

#### Features
- **Server**: 
  - Handle multiple client connections and manage user interactions.
  - Support for user authentication and unique nickname assignment.
  - Broadcast messages to all users and facilitate private messaging.
  
- **Client**:
  - Provide a command-line interface for user interaction.
  - Allow users to input their nickname and connect to the server.
  - Display chat messages and handle user commands effectively.

#### Considerations
- Adhere to the principles of **OOP**, **SOLID**, and relevant design patterns throughout the implementation.
- Focus on writing clean, maintainable, and efficient code.
- Assume any unknowns and creatively enhance the problem statement to demonstrate your capabilities.

---

