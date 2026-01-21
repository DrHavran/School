import java.io.File;
import java.util.*;

public class Data {
    ArrayList<String> attributes;
    ArrayList<HashMap<String, String>> points;

    public Data() {
        this.points = new ArrayList<>();
        this.attributes = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        try{
            Scanner sc = new Scanner(new File("animals_dataset.csv"));
            String line = sc.nextLine();
            attributes.addAll(List.of(line.split(",")));
            System.out.println(Arrays.toString(attributes.toArray()));

            while(sc.hasNextLine()) {
                HashMap<String, String> point = new HashMap<>();
                line = sc.nextLine();
                String[] data = line.split(",");
                for(int i = 0; i < data.length; i++) {
                    point.put(attributes.get(i), data[i]);
                }
                points.add(point);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<HashMap<String, String>> getPoints() {
        return points;
    }
    public ArrayList<String> getAttributes() {
        return attributes;
    }
}