package org.example;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Logic {
    Hashtable<Long, Node> nodes;
    private double maxX = 0;
    private double minX = Double.MAX_VALUE;
    private double maxY = 0;
    private double minY = Double.MAX_VALUE;
    private double scale;

    public Logic() {
        nodes = new Hashtable<>();
        loadFile();
    }

    private void loadFile(){
        try{
            Scanner sc = new Scanner(new File("map.osm"));
            sc.nextLine();
            sc.nextLine();
            loadBounds(sc.nextLine());
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("<node")){
                    createNode(line);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public double scaleX(double number){
        return (number - minX) / (maxX - minX) * Settings.screenWidth;
    }
    public double scaleY(double number){
        return Settings.screenHeight - (number-minY) / (maxY - minY) * Settings.screenHeight;
    }

    private void loadBounds(String line){
        String minLat = line.split("minlat")[1].split(" ")[0];
        minLat = minLat.substring(minLat.indexOf('"') + 1, minLat.lastIndexOf('"'));
        this.minY = minLat.substring(0, 8);
    }

    private void createNode(String line){
        String latNumber = line.split("lat")[1].split(" ")[0];
        latNumber = latNumber.substring(latNumber.indexOf('"') + 1, latNumber.lastIndexOf('"'));
        latNumber = latNumber.substring(0, 8);

        String lonNumber = line.split("lon")[1].split(" ")[0];
        lonNumber = lonNumber.substring(lonNumber.indexOf('"') + 1, lonNumber.lastIndexOf('"'));
        lonNumber = lonNumber.substring(0, 8);

        String idNumber = line.split("id")[1].split(" ")[0];
        idNumber = idNumber.substring(idNumber.indexOf('"') + 1, idNumber.lastIndexOf('"'));

        double lat = Double.parseDouble(latNumber);
        double lon = Double.parseDouble(lonNumber);
        long id = Long.parseLong(idNumber);

        System.out.println(lat + " " + lon);
        Node node = new Node(lat, lon);
        nodes.put(id, node);
    }

    public Node getNode(long index) {
        return nodes.get(index);
    }
    public Hashtable<Long, Node> getNodes(){
        return nodes;
    }
}