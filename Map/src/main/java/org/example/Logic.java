package org.example;

import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Logic {
    Hashtable<Long, Node> nodes;
    private final Draw draw;

    private double maxX = 0;
    private double minX = Double.MAX_VALUE;
    private double maxY = 0;
    private double minY = Double.MAX_VALUE;

    public Logic(Pane root) {
        nodes = new Hashtable<>();
        draw = new Draw(root);
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
                }else if(line.contains("<way")){
                    line = sc.nextLine();
                    Node first;
                    Node second;

                    String id = line.split("ref=")[1]; //loads the first 2 nodes before starting the loop
                    id = id.substring(id.indexOf('"') + 1, id.lastIndexOf('"'));
                    first = nodes.get(Long.parseLong(id));
                    line = sc.nextLine();

                    id = line.split("ref=")[1];
                    id = id.substring(id.indexOf('"') + 1, id.lastIndexOf('"'));
                    second = nodes.get(Long.parseLong(id));
                    draw.drawLine(first, second);
                    line =  sc.nextLine();

                    while(line.contains("<nd")){
                        first = second;

                        id = line.split("ref=")[1];
                        id = id.substring(id.indexOf('"') + 1, id.lastIndexOf('"'));
                        second = nodes.get(Long.parseLong(id));
                        if(first != null && second != null){
                            draw.drawLine(first, second);
                        }
                        line = sc.nextLine();
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void loadBounds(String line){
        String minLat = line.split("minlat=")[1].split(" ")[0];
        minLat = minLat.substring(minLat.indexOf('"') + 1, minLat.lastIndexOf('"'));
        this.minY = Double.parseDouble(minLat);

        String maxLat = line.split("maxlat=")[1].split(" ")[0];
        maxLat = maxLat.substring(maxLat.indexOf('"') + 1, maxLat.lastIndexOf('"'));
        this.maxY = Double.parseDouble(maxLat);

        String minLon = line.split("minlon=")[1].split(" ")[0];
        minLon = minLon.substring(minLon.indexOf('"') + 1, minLon.lastIndexOf('"'));
        this.minX = Double.parseDouble(minLon);

        String maxLon = line.split("maxlon=")[1].split(" ")[0];
        maxLon = maxLon.substring(maxLon.indexOf('"') + 1, maxLon.lastIndexOf('"'));
        this.maxX = Double.parseDouble(maxLon);
    }

    private void createNode(String line){
        String latNumber = line.split("lat=")[1].split(" ")[0];
        latNumber = latNumber.substring(latNumber.indexOf('"') + 1, latNumber.lastIndexOf('"'));

        String lonNumber = line.split("lon=")[1].split(" ")[0];
        lonNumber = lonNumber.substring(lonNumber.indexOf('"') + 1, lonNumber.lastIndexOf('"'));

        String idNumber = line.split("id=")[1].split(" ")[0];
        idNumber = idNumber.substring(idNumber.indexOf('"') + 1, idNumber.lastIndexOf('"'));

        double lat = Double.parseDouble(latNumber);
        double lon = Double.parseDouble(lonNumber);
        long id = Long.parseLong(idNumber);

        System.out.println(lat + " " + lon);
        Node node = new Node(lon, lat, scaleX(lon), scaleY(lat));
        nodes.put(id, node);
        draw.drawDot(node);
    }

    public double scaleX(double number){
        return (number - minX) / (maxX - minX) * Settings.screenWidth;
    }
    public double scaleY(double number){
        return Settings.screenHeight - (number-minY) / (maxY - minY) * Settings.screenHeight;
    }
}