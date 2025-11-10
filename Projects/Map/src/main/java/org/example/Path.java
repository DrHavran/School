package org.example;

public class Path {
    private final Node start;
    private final Node end;

    public Path(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
