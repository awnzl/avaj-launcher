package src;

import java.io.FileWriter;

/**
 * Created by avenzel on 11/18/17.
 */
public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    
    protected Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions () {
        StringBuilder sb = new StringBuilder();

        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": Let's enjoy the good weather and take some pics.\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), ((this.coordinates.getHeight() + 4) > 100) ? 100 : this.coordinates.getHeight() + 4);
                break;
            case "RAIN":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": Damn you rain! You messed up my baloon.\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
                break;
            case "FOG":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": This fog... can't see anything...\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
                break;
            case "SNOW":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": It's snowing. We're gonna crash.\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 15) < 0 ? 0 : this.coordinates.getHeight() - 15);
                break;
        }

        try {
            FileWriter fw = new FileWriter("simulation.txt", true);

            if (this.coordinates.getHeight() <= 0) {
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": landing.\n");
                fw.append(sb.toString());
                fw.close();
                weatherTower.unregister(this);
            } else {
                fw.append(sb.toString());
                fw.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
