package test.opakovani;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Data {

    private final HashMap<String, Country> countries;

    public Data() {
        this.countries = new HashMap<>();
        loadData();
    }

    private void loadData(){
        try{
            Scanner sc = new Scanner(new File("mean-years-of-schooling-long-run.csv"));
            sc.nextLine();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");

                String entity = parts[0];
                String code = parts[1];
                int year = Integer.parseInt(parts[2]);
                double value = Double.parseDouble(parts[3]);

                if(countries.containsKey(entity)){
                    countries.get(entity).addEntry(year, value);
                }else{
                    Country country = new Country(entity, code);
                    country.addEntry(year, value);
                    countries.put(entity, country);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, Country> getCountries() {
        return countries;
    }
}