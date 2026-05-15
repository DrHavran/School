package example.springboottest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gapminder")
public class CountryController {
    private final Data data = new Data("gapminderDataFiveYear.csv");


    @GetMapping("year/{year}")
    public ArrayList<CountryPayload> getRecords(@PathVariable String year, @RequestParam String continent){
        ArrayList<Country> continentCountries = data.getDataFromSpecificContinent(continent);
        return continentCountries.stream()
                .map( country -> {
                    HashMap<String, Double> innerMap = country.getYearInfo(Integer.parseInt(year));


                    return new CountryPayload(
                            country.getName(),
                            Integer.parseInt(year),
                            innerMap.get("population"),
                            country.getContinent(),
                            innerMap.get("lifeExp"),
                            innerMap.get("gdpPerCap")
                    );
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping("continents/{continent}/stats")
    public StatsPayload getStats(@PathVariable String continent, @RequestParam String year){
        ArrayList<Country> continentCountries = data.getDataFromSpecificContinent(continent);

        Double totalPopulation = continentCountries.stream()
                .map(country -> country.getYearInfo(Integer.parseInt(year)))
                .mapToDouble(countyInfo -> countyInfo.get("population"))
                .sum();

        Double averageLifeExp = continentCountries.stream()
                .map(country -> country.getYearInfo(Integer.parseInt(year)))
                .mapToDouble(countyInfo -> countyInfo.get("lifeExp"))
                .sum() / continentCountries.size();

        Double averageGdpPerCap = continentCountries.stream()
                .map(country -> country.getYearInfo(Integer.parseInt(year)))
                .mapToDouble(countyInfo -> countyInfo.get("gdpPerCap"))
                .sum() / continentCountries.size();

        String highestLifeExpCountry = "";
        Double lifeExp = 0.00;

        for(Country country : continentCountries){
            Double lifeExpCountry = country.getYearInfo(Integer.parseInt(year)).get("lifeExp");
            if(lifeExpCountry > lifeExp){
                lifeExp = lifeExpCountry;
                highestLifeExpCountry = country.getName();
            }
        }

        String lowestLifeExpCountry = "";
        lifeExp = Double.MAX_VALUE;

        for(Country country : continentCountries){
            Double lifeExpCountry = country.getYearInfo(Integer.parseInt(year)).get("lifeExp");
            if(lifeExpCountry < lifeExp){
                lifeExp = lifeExpCountry;
                lowestLifeExpCountry = country.getName();
            }
        }

        return new StatsPayload(
                continent,
                Integer.parseInt(year),
                continentCountries.size(),
                totalPopulation,
                averageLifeExp,
                averageGdpPerCap,
                highestLifeExpCountry,
                lowestLifeExpCountry
        );
    }

    @GetMapping("life-expectancy/improvement")
    public ArrayList<LifeExpPayload> lifeExp(@RequestParam String from, @RequestParam String to, @RequestParam String limit){
        ArrayList<Country> countries = data.getCountries();
        HashMap<Country, Double> improvements = new HashMap<>();

        for(Country country : countries){
            Double lifeExpStart = country.getYearInfo(Integer.parseInt(from)).get("lifeExp");
            Double lifeExpEnd = country.getYearInfo(Integer.parseInt(to)).get("lifeExp");
            Double difference = lifeExpEnd - lifeExpStart;

            improvements.put(country, difference);
        }

        ArrayList<Country> sorted = improvements.keySet().stream()
                .sorted((Comparator.comparing(improvements::get)))
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.reverse(sorted);

        return sorted.stream()
                .limit(Integer.parseInt(limit))
                .map(country ->
                        new LifeExpPayload(
                                country.getName(),
                                country.getContinent(),
                                Integer.parseInt(from),
                                Integer.parseInt(to),
                                country.getYearInfo(Integer.parseInt(from)).get("lifeExp"),
                                country.getYearInfo(Integer.parseInt(to)).get("lifeExp"),
                                improvements.get(country)
                        )
                )
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
