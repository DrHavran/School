package org.example;

public class Node {
    private final double longitude; //X
    private final double latitude;  //Y
    private final double boardX;
    private final double boardY;

    public Node(double longitude, double latitude, double boardX, double boardY) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getBoardX() { return boardX; }
    public double getBoardY() { return boardY; }
}