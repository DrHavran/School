package example;

import example.Parts.Node;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Data {
    private final HashSet<Node> nodes;

    private double minX, minY, maxX, maxY;

    public Data() {
        this.nodes = new HashSet<>();
        loadData();
    }

    private void loadData() {
        minX = Double.POSITIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;
        try {
            Scanner sc = new Scanner(new File("mole-mouse.csv"));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                double x = Double.parseDouble(line[0]);
                if(x < minX) minX = x;
                if(x > maxX) maxX = x;

                double y = Double.parseDouble(line[1]);
                if(y < minY) minY = y;
                if(y > maxY) maxY = y;

                nodes.add(new Node(x, y, Double.parseDouble(line[2])));
            }
            sc.close();

            for (Node node : nodes) {
                node.set(
                        transformX(node.getX()),
                        transformY(node.getY())
                );
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private double transformX(double x) {
        return (x - minX) / (maxX - minX) * Settings.screenWidth;
    }
    private double transformY(double y) {
        return Settings.screenHeight - (y - minY) / (maxY - minY) * Settings.screenHeight;
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }
}
