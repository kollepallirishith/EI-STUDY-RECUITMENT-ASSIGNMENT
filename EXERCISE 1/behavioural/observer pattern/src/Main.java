import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class GameEventManager implements GameEvent {
    private List<Observer> observers;

    public GameEventManager() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String eventType, String message) {
        for (Observer observer : observers) {
            observer.update(eventType, message);
        }
    }

    // Encapsulation: Trigger events
    public void triggerHealthChange(int newHealth) {
        String message = "Player health is now " + newHealth;
        notifyObservers("HealthChange", message);
    }

    public void triggerEnemyAttack(String enemyName) {
        String message = enemyName + " is attacking!";
        notifyObservers("EnemyAttack", message);
    }
}
class HealthBar implements Observer {
    @Override
    public void update(String eventType, String message) {
        if ("HealthChange".equals(eventType)) {
            System.out.println("HealthBar updated: " + message);
        }
    }
}
abstract class GameEntity {
    private String name;  // Encapsulated fields

    public GameEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method to be implemented by concrete entities
    public abstract void performAction();
}


class Enemy extends GameEntity implements Observer {
    public Enemy(String name) {
        super(name);  // Inheritance: Call to superclass constructor
    }

    // Polymorphism: Enemy reacts to different events
    @Override
    public void update(String eventType, String message) {
        if ("HealthChange".equals(eventType)) {
            System.out.println(getName() + " doesn't care about health updates.");
        } else if ("EnemyAttack".equals(eventType)) {
            System.out.println(getName() + " prepares to support the attack: " + message);
        }
    }

    @Override
    public void performAction() {
        System.out.println(getName() + " is patrolling the area...");
    }
}
class Player extends GameEntity implements Observer {
    private int health;  // Encapsulation: Private field for player health

    public Player(String name, int initialHealth) {
        super(name);  // Inheritance: Call to superclass constructor
        this.health = initialHealth;
    }

    public int getHealth() {
        return health;
    }

    // Encapsulation: Method to modify player health
    public void setHealth(int health) {
        this.health = health;
    }

    // Polymorphism: Player reacts differently to different events
    @Override
    public void update(String eventType, String message) {
        if ("HealthChange".equals(eventType)) {
            System.out.println(getName() + " received health update: " + message);
        } else if ("EnemyAttack".equals(eventType)) {
            System.out.println(getName() + " prepares for battle: " + message);
        }
    }

    @Override
    public void performAction() {
        System.out.println(getName() + " is exploring the game world...");
    }
}

class SoundManager implements Observer {
    @Override
    public void update(String eventType, String message) {
        System.out.println("SoundManager plays sound for event [" + eventType + "]: " + message);
    }
}

public class Main {
    public static void main(String[] args) {

        GameEventManager gameEventManager = new GameEventManager();


        Player player = new Player("Hero", 100);
        Enemy enemy = new Enemy("Goblin");


        HealthBar healthBar = new HealthBar();
        SoundManager soundManager = new SoundManager();

        // Register Observers with the GameEventManager
        gameEventManager.registerObserver(player);
        gameEventManager.registerObserver(enemy);
        gameEventManager.registerObserver(healthBar);
        gameEventManager.registerObserver(soundManager);


        player.performAction();
        enemy.performAction();


        System.out.println("\n>>> Simulating Player Health Change Event");
        gameEventManager.triggerHealthChange(75);

        System.out.println("\n>>> Simulating Enemy Attack Event");
        gameEventManager.triggerEnemyAttack("Goblin");


        gameEventManager.removeObserver(healthBar);

        System.out.println("\n>>> Simulating Another Player Health Change Event After Removing HealthBar");
        gameEventManager.triggerHealthChange(50);
    }
}
