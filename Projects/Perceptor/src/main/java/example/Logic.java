package example;

import example.Parts.Node;

import java.util.HashSet;

public class Logic {
    private final Data data;

    public Logic() {
        this.data = new Data();
    }

    public HashSet<Node> getNodes() {
        return data.getNodes();
    }
}