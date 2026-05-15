package example.springboottest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Data {

    private final HashMap<String, Country> countries;

    public Data(String url) {
        this.countries = new HashMap<>();
        try {
            Scanner sc = new Scanner( new File(url) );

            sc.nextLine();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String name = line.split(",")[0];

                if(countries.containsKey(name)){
                    countries.get(name).addInfo(line);
                }else{
                    Country country = new Country(line);
                    countries.put(name, country);
                }
            }
            sc.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Country> getDataFromSpecificContinent(String continent){
        return countries.values().stream()
                .filter( country -> country.getContinent().equals(continent))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Country> getCountries(){
        ArrayList<Country> list = new ArrayList<>();
        list.addAll(countries.values());
        return list;
    }
}
