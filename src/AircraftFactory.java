package src;

class AircraftFactory {
    Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Exception {
        Flyable vehicle;

        switch (type) {
            case "Helicopter":
                vehicle = new Helicopter(name, new Coordinates(longitude, latitude, height));
                break;
            case "JetPlane":
                vehicle = new JetPlane(name, new Coordinates(longitude, latitude, height));
                break;
            case "Baloon":
                vehicle = new Baloon(name, new Coordinates(longitude, latitude, height));
                break;
            default:
                throw new IllegalArgumentException("Illegal aircraft type");
        }

        return (vehicle);
    }
}