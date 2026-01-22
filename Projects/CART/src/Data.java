import java.io.File;
import java.util.*;

public class Data {
    ArrayList<String> attributes;
    ArrayList<HashMap<String, String>> points;
    ArrayList<HashMap<String, String>> testPoints;

    public Data() {
        this.points = new ArrayList<>();
        this.testPoints = new ArrayList<>();
        this.attributes = new ArrayList<>();
        loadData(points, "animals_dataset.csv");
        loadData(testPoints, "testAnimals.csv");
        System.out.println(points.size() + " points loaded");
        System.out.println(testPoints.size() + " test points loaded");
    }

    private void loadData(ArrayList<HashMap<String, String>> list, String fileName) {
        try{
            Scanner sc = new Scanner(new File(fileName));
            String line = sc.nextLine();

            if(attributes.isEmpty()){
                attributes.addAll(List.of(line.split(",")));
                System.out.println(Arrays.toString(attributes.toArray()));
            }

            while(sc.hasNextLine()) {
                HashMap<String, String> point = new HashMap<>();
                line = sc.nextLine();
                String[] data = line.split(",");
                for(int i = 0; i < data.length; i++) {
                    point.put(attributes.get(i), data[i]);
                }
                list.add(point);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<HashMap<String, String>> getPoints() {
        return points;
    }
    public ArrayList<HashMap<String, String>> getTestPoints() {
        return testPoints;
    }
    public ArrayList<String> getAttributes() {
        return attributes;
    }
}