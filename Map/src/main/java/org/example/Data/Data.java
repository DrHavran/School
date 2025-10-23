package org.example.Data;

import org.example.Node;
import org.example.Logic.NodeModel;
import org.example.Path;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Data {

    Hashtable<Long, Node> nodes;
    ArrayList<Path> paths;

    public Data() {
        nodes = new Hashtable<>();
        paths = new ArrayList<>();
        loadFile();
        loadFileDocument();
    }

    public Hashtable<Long, Node> getNodes() {
        return nodes;
    }
    public ArrayList<Path> getPaths() {
        return paths;
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

                    paths.add(new Path(first, second));
                    first.addNode(second);
                    second.addNode(first);
                    line = sc.nextLine();

                    while(line.contains("<nd")){
                        first = second;

                        id = line.split("ref=")[1];
                        id = id.substring(id.indexOf('"') + 1, id.lastIndexOf('"'));
                        second = nodes.get(Long.parseLong(id));
                        if(first != null && second != null){
                            first.addNode(second);
                            second.addNode(first);
                            paths.add(new Path(first, second));
                        }
                        line = sc.nextLine();
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void loadFileDocument(){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File("map.osm"));
            doc.getDocumentElement().normalize();



        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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

        System.out.println("Node - " + id + ": " + lat + " " + lon);
        Node node = new Node(lon, lat);
        nodes.put(id, node);
    }

    public NodeModel createModel(){
        try{
            Scanner sc = new Scanner(new File("map.osm"));
            sc.nextLine();
            sc.nextLine();
            return loadBounds(sc.nextLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private NodeModel loadBounds(String line){
        String minLat = line.split("minlat=")[1].split(" ")[0];
        minLat = minLat.substring(minLat.indexOf('"') + 1, minLat.lastIndexOf('"'));
        double minY = Double.parseDouble(minLat);

        String maxLat = line.split("maxlat=")[1].split(" ")[0];
        maxLat = maxLat.substring(maxLat.indexOf('"') + 1, maxLat.lastIndexOf('"'));
        double maxY = Double.parseDouble(maxLat);

        String minLon = line.split("minlon=")[1].split(" ")[0];
        minLon = minLon.substring(minLon.indexOf('"') + 1, minLon.lastIndexOf('"'));
        double minX = Double.parseDouble(minLon);

        String maxLon = line.split("maxlon=")[1].split(" ")[0];
        maxLon = maxLon.substring(maxLon.indexOf('"') + 1, maxLon.lastIndexOf('"'));
        double maxX = Double.parseDouble(maxLon);

        return new NodeModel(maxX, minX, maxY, minY);
    }
}
