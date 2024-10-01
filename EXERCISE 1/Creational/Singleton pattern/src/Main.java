import java.util.Stack;

// 1. Singleton Class: UndoManager
class UndoManager {
    // Static instance for Singleton
    private static UndoManager instance;

    // Stack to keep track of actions for undo
    private Stack<String> actions;

    // Private constructor to prevent instantiation
    private UndoManager() {
        actions = new Stack<>();
    }

    // Public method to get the Singleton instance
    public static UndoManager getInstance() {
        if (instance == null) {
            instance = new UndoManager();
        }
        return instance;
    }

    // Method to add an action to the undo stack
    public void addAction(String action) {
        actions.push(action);
        System.out.println("Action added: " + action);
    }

    // Method to undo the last action
    public void undo() {
        if (!actions.isEmpty()) {
            String action = actions.pop();
            System.out.println("Undo action: " + action);
        } else {
            System.out.println("No actions to undo.");
        }
    }

    // Method to check the number of actions
    public int getActionCount() {
        return actions.size();
    }
}

// 2. Main Program: Demonstrates the Singleton UndoManager
public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of UndoManager
        UndoManager undoManager = UndoManager.getInstance();

        // Add some actions to the undo stack
        undoManager.addAction("Typed 'Hello'");
        undoManager.addAction("Changed font to Bold");
        undoManager.addAction("Inserted Image");

        // Perform undo operations
        undoManager.undo(); // Undo "Inserted Image"
        undoManager.undo(); // Undo "Changed font to Bold"

        // Add another action and undo it
        undoManager.addAction("Deleted text");
        undoManager.undo(); // Undo "Deleted text"

        // Attempt to undo when there are no actions left
        undoManager.undo(); // "No actions to undo"
    }
}
