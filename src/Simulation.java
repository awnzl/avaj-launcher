package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main
 */
public class Simulation {
    private static AircraftFactory afactory = new AircraftFactory();
    
    public static void main(String args[]) throws Exception{
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(args[0]));

            String s = bfr.readLine();
            if (s != null) {
                Integer simulations = Integer.parseInt(s);
                if (simulations < 0) {
                    System.out.println("Wrong number of simulations");
                    System.exit(1);
                }

                new File("simulation.txt");

                WeatherTower weatherTower = new WeatherTower();

                String planeInfo[];
                Flyable fl;
                while ((s = bfr.readLine()) != null) {
                    planeInfo = s.split(" ");
                    if (planeInfo.length != 5)
                        throw new IllegalArgumentException("Wrong number of input data for aircraft");
                    fl = afactory.newAircraft(planeInfo[0], planeInfo[1],
                            Integer.parseInt(planeInfo[2]), Integer.parseInt(planeInfo[3]),
                            Integer.parseInt(planeInfo[4]));
                    fl.registerTower(weatherTower);
                    weatherTower.register(fl);
                }

                //here is the simulations
                for (int i = 0; i < simulations; i++) {
                    weatherTower.changeWeather();
                }
            } else
                throw new IllegalArgumentException("File is empty");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("No such file: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Something crash and this is an exception with message: " + ex.getMessage());
        }

    }
}