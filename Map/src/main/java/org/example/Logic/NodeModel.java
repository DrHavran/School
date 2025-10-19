package org.example.Logic;

public class NodeModel {

    private double maxX = 0;
    private double minX = Double.MAX_VALUE;
    private double maxY = 0;
    private double minY = Double.MAX_VALUE;

    public NodeModel(double maxX, double minX, double maxY, double minY) {
        this.maxX = maxX;
        this.minX = minX;
        this.maxY = maxY;
        this.minY = minY;
    }

    public double scaleX(double number){
        return (number - minX) / (maxX - minX) * Settings.screenWidth;
    }
    public double scaleY(double number){
        return Settings.screenHeight - (number-minY) / (maxY - minY) * Settings.screenHeight;
    }

}
