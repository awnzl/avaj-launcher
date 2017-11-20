package src;

/**
 * Flyable
 */
public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower wt);
}