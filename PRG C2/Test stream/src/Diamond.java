public class Diamond {
    private final int id;
    private final double carat;
    private final String cut;
    private final String color;
    private final String clarity;
    private final double depth;
    private final double table;
    private final double price;
    private final double x, y, z;

    public Diamond(String line) {
        String[] values = line.split(",");
        id = Integer.parseInt(values[0].replace('"', ' ').trim());
        carat = Double.parseDouble(values[1]);
        cut = values[2].replace('"', ' ').trim();
        color = values[3].replace('"', ' ').trim();
        clarity = values[4].replace('"', ' ').trim();
        depth = Double.parseDouble(values[5]);
        table = Double.parseDouble(values[6]);
        price = Double.parseDouble(values[7]);
        x = Double.parseDouble(values[8]);
        y = Double.parseDouble(values[9]);
        z = Double.parseDouble(values[10]);
    }

    public String getCut() {
        return cut;
    }

    public double getPrice() {
        return price;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getColor() {
        return color;
    }
}
