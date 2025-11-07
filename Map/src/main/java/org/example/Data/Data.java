package org.example.Data;

import org.example.Node;
import org.example.Logic.NodeModel;
import org.example.Path;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class Data {

    Hashtable<Long, Node> nodes;
    ArrayList<Path> paths;
    NodeModel model;

    public Data() {
        nodes = new Hashtable<>();
        paths = new ArrayList<>();
        loadFile();
        System.out.println("Loaded " + nodes.size() + " nodes");
    }

    public Hashtable<Long, Node> getNodes() {
        return nodes;
    }
    public ArrayList<Path> getPaths() {
        return paths;
    }

    private void loadFile(){
        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(
                    new FileInputStream("map.osm")
            );

            while(reader.hasNext()){
                int event = reader.next();

                if(event == XMLStreamReader.START_ELEMENT){
                    String name = reader.getLocalName();

                    if("node".equals(name)){
                        double lat = Double.parseDouble(reader.getAttributeValue(null, "lat"));
                        double lon = Double.parseDouble(reader.getAttributeValue(null, "lon"));
                        long id = Long.parseLong(reader.getAttributeValue(null, "id"));

                        System.out.println("Node - " + id + ": " + lat + " " + lon);
                        Node node = new Node(lon, lat);
                        nodes.put(id, node);
                    }else if("way".equals(name)){
                        Node first = null;
                        Node second = null;
                        while(reader.hasNext()){
                            event = reader.next();
                            if(event == XMLStreamReader.START_ELEMENT){
                                if(reader.getLocalName().equals("nd")){
                                    if(first == null){
                                        first = nodes.get(Long.parseLong(reader.getAttributeValue(null, "ref")));
                                    }else if (second == null) {
                                        second = nodes.get(Long.parseLong(reader.getAttributeValue(null, "ref")));
                                        paths.add(new Path(first, second));
                                        first.addNode(second);
                                        second.addNode(first);
                                    }else{
                                        first = second;
                                        second = nodes.get(Long.parseLong(reader.getAttributeValue(null, "ref")));
                                        if(second != null){
                                            first.addNode(second);
                                            second.addNode(first);
                                            paths.add(new Path(first, second));
                                        }
                                    }
                                }else{
                                    break;
                                }
                            }

                        }
                    }else if("bounds".equals(name)){
                        double minY = Double.parseDouble(reader.getAttributeValue(null, "minlat"));
                        double maxY = Double.parseDouble(reader.getAttributeValue(null, "maxlat"));
                        double minX = Double.parseDouble(reader.getAttributeValue(null, "minlon"));
                        double maxX = Double.parseDouble(reader.getAttributeValue(null, "maxlon"));

                        this.model =  new NodeModel(maxX, minX, maxY, minY);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public double scaleX(Double number){
        return model.scaleX(number);
    }
    public double scaleY(Double number){
        return model.scaleY(number);
    }
}
