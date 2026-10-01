package test.opakovani;

import java.util.HashMap;

public class Country {
    private final String entity;
    private final String code;
    private final HashMap<Integer, Double> entries;

    public Country(String entity, String code) {
        this.entity = entity;
        this.code = code;
        this.entries = new HashMap<>();
    }

    public String getEntity() {
        return entity;
    }
    public String getCode() {
        return code;
    }
    public HashMap<Integer, Double> getEntries() {
        return entries;
    }
    public void addEntry(int year, double value){
        entries.put(year, value);
    }

    @Override
    public String toString() {
        return "Country{" +
                "entity='" + entity + '\'' +
                ", code='" + code + '\'' +
                ", entries=" + entries +
                '}';
    }
}
