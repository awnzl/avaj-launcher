package src;

import java.util.Random;

class WeatherProvider {
    private static WeatherProvider provider;
    private static String[] weather = {"SUN", "FOG", "RAIN", "SNOW"};

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        if (provider == null)
            provider = new WeatherProvider();
        
        return (provider);
    }

    public String getCurrentWeather(Coordinates coordinates) {
        if (coordinates.getHeight() > 70)
            return (weather[(new Random().nextInt(3)) + 1]);
        return (weather[new Random().nextInt(4)]);
    }
}