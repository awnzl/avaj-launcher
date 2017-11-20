package src;

import java.io.FileWriter;

/**
 * Created by avenzel on 11/18/17.
 */
public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    
    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        StringBuilder sb = new StringBuilder();

        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": Hello sunny!\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, ((this.coordinates.getHeight() + 2) > 100) ? 100 : this.coordinates.getHeight() + 2);
                break;
            case "RAIN":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": It's raining. Better watch out for lightings.\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
                break;
            case "FOG":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": Can't see anything! Where are you, tower???\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
                break;
            case "SNOW":
                sb.append(this.getClass().getSimpleName()).append('#').append(this.name).append('(').append(this.id).append(')').append(": OMG! Winter is coming!\n");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 7) < 0 ? 0 : this.coordinates.getHeight() - 7);
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
