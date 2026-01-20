import java.io.File;
import java.util.*;

public class Data {
    ArrayList<String> attributes;
    ArrayList<HashMap<String, String>> animals;

    public Data() {
        this.animals = new ArrayList<>();
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
                HashMap<String, String> animal = new HashMap<>();
                line = sc.nextLine();
                String[] data = line.split(",");
                for(int i = 0; i < data.length; i++) {
                    animal.put(attributes.get(i), data[i]);
                }
                animals.add(animal);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<HashMap<String, String>> getAnimals() {
        return animals;
    }
    public ArrayList<String> getAttributes() {
        return attributes;
    }
}