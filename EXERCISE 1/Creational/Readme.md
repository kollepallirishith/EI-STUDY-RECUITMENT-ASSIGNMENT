
# Creational Design Patterns

Creational design patterns deal with object creation mechanisms, providing various ways to instantiate objects in a flexible and efficient manner. This README covers two commonly used creational design patterns: **Factory** and **Singleton** patterns.

## 1. Factory Pattern

### Overview

The Factory Pattern provides an interface for creating objects in a super class but allows subclasses to alter the type of objects that will be created. It abstracts the instantiation process, promoting loose coupling and enhancing code maintainability.

### Use Cases

- **Color Fill Application**: In a graphic design or painting application, the Factory Pattern can be used to create different color objects based on user selection. For example, when a user chooses a color to fill a shape, the application can dynamically create instances of color classes (like Red, Green, or Blue) without needing to hard-code specific classes.
  
- **Animal Creation**: In a simulation or gaming application, the Factory Pattern can facilitate the creation of various animal objects (such as Dog, Cat, or Bird) based on user input or game scenarios. This allows for easier extension of the animal types without modifying existing code.

## 2. Singleton Pattern

### Overview

The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance. It is particularly useful in scenarios where exactly one object is required to coordinate actions throughout the system.

### Use Case

- **Undo Manager**: In applications like text editors, an Undo Manager can be implemented as a Singleton. This ensures that there is only one instance responsible for managing the history of actions, allowing users to perform undo operations consistently across the application. By centralizing the undo functionality, it simplifies the management of user actions and their reversal.

## Conclusion

Both the Factory and Singleton patterns are essential tools in a developer's toolkit for managing object creation and lifecycle. The Factory Pattern enhances flexibility and reusability by decoupling object creation from specific implementations, while the Singleton Pattern provides a controlled access point for a single instance of a class. Understanding and applying these patterns can significantly improve the design and maintainability of software projects.
