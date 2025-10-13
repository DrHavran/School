package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    ArrayList<Node> nodes;
    private double maxX = 0;
    private double minY = Double.MAX_EXPONENT;

    public Logic() {
        nodes = new ArrayList<>();
        loadFile();
    }

    private void loadFile(){
        try{
            Scanner sc = new Scanner(new File("map.osm"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("<node")){
                    nodes.add(createNode(line));
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private Node createNode(String line){
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

        if(lon>maxX){
            maxX = lon;
        }
        if(lon<minY){
            minY = lon;
        }

        System.out.println(lat + " " + lon + " " + id);

        return new Node(lat, lon, id);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}