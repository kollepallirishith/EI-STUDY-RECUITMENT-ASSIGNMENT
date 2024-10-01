interface GameEvent {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String eventType, String message);
}