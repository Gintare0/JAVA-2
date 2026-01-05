import java.util.Random;

public class GeoLocation {

    private double lat;
    private double lon;

    private static int numLocations = 0;

    public GeoLocation() {
        Random random = new Random();
        lat = -90 + 180 * random.nextDouble();
        lon = -90 + 180 * random.nextDouble();

        lat = Math.round(lat * 1_000_000.0) / 1_000_000.0;
        lon = Math.round(lon * 1_000_000.0) / 1_000_000.0;

        numLocations++;
    }

    public GeoLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        numLocations++;
    }

    public GeoLocation(GeoLocation other) {
        Random random = new Random();
        this.lat = other.lat + (-0.1 + 0.2 * random.nextDouble());
        this.lon = other.lon + (-0.1 + 0.2 * random.nextDouble());
        numLocations++;
    }

    public void print() {
        System.out.println("[" + lat + ", " + lon + "]");
    }

   
    public static double distance(GeoLocation a, GeoLocation b) {
        double R = 6371.0;

        double lat1 = Math.toRadians(a.lat);
        double lat2 = Math.toRadians(b.lat);
        double dLat = Math.toRadians(b.lat - a.lat);
        double dLon = Math.toRadians(b.lon - a.lon);

        double h = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
        double dist = R * c;

        return Math.round(dist * 10.0) / 10.0;
    }

    public static int getNumLocations() {
        return numLocations;
    }
}
