package example.springboottest;

import java.util.HashMap;

public class Country {
    private final String name;
    private final String continent;
    private final HashMap<Integer, HashMap<String, Double>> info;

    public Country(String line) {
        this.info = new HashMap<>();
        String[] parts = line.split(",");

        this.name = parts[0];
        this.continent = parts[3];
        HashMap<String, Double> innerMap = new HashMap<>();

        int year = Integer.parseInt(parts[1]);

        Double population = Double.parseDouble(parts[2]);
        Double lifeExp = Double.parseDouble(parts[4]);
        Double gdpPerCap = Double.parseDouble(parts[5]);

        innerMap.put("population", population);
        innerMap.put("lifeExp", lifeExp);
        innerMap.put("gdpPerCap", gdpPerCap);

        info.put(year, innerMap);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Double> getYearInfo(int year){
        return info.get(year);
    }

    public String getContinent() {
        return continent;
    }

    public void addInfo(String line){
        String[] parts = line.split(",");

        HashMap<String, Double> innerMap = new HashMap<>();

        int year = Integer.parseInt(parts[1]);

        Double population = Double.parseDouble(parts[2]);
        Double lifeExp = Double.parseDouble(parts[4]);
        Double gdpPerCap = Double.parseDouble(parts[5]);

        innerMap.put("population", population);
        innerMap.put("lifeExp", lifeExp);
        innerMap.put("gdpPerCap", gdpPerCap);

        info.put(year, innerMap);
    }
}
