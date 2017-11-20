package src;

class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}