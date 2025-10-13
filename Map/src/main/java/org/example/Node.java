package org.example;

public class Node {
    private final double latitude;
    private final double longitude;
    private final long id;

    public Node(double latitude, double longitude, long id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public long getId() {
        return id;
    }
}
