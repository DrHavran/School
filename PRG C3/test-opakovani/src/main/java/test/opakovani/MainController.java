package test.opakovani;

import org.springframework.web.bind.annotation.*;
import test.opakovani.models.BestCountryViewModel;
import test.opakovani.models.CountryChangeViewModel;
import test.opakovani.models.StatsViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import static java.util.stream.Collectors.toCollection;

@RestController
@RequestMapping("api/schooling")
public class MainController {
    private final Data data;

    public MainController() {
        this.data = new Data();
    }

    @GetMapping("/top-countries")
    public ArrayList<BestCountryViewModel> getBestCountries(
            @RequestParam int year,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Integer limit
    ){
        ArrayList<Country> countriesForTheYear =
                data.getCountries().values().stream()
                        .filter(c -> c.getEntries().containsKey(year))
                        .collect(toCollection(ArrayList::new));

        double averageSchooling = countriesForTheYear.stream()
                .mapToDouble(c -> c.getEntries().get(year)).sum() / countriesForTheYear.size();

        ArrayList<BestCountryViewModel> viewModels = countriesForTheYear.stream()
                .map(c -> new BestCountryViewModel(
                        c.getEntity(),
                        c.getCode(),
                        year,
                        c.getEntries().get(year),
                        Math.abs(averageSchooling - c.getEntries().get(year))
                ))
                .sorted(Comparator.comparingDouble(BestCountryViewModel::getAverageYearsOfSchooling).reversed())
                .collect(toCollection(ArrayList::new));

        if(min != null){
            viewModels = viewModels.stream()
                    .filter(c -> c.getAverageYearsOfSchooling() > min)
                    .collect(toCollection(ArrayList::new));
        }
        if(limit != null){
            viewModels = viewModels.stream()
                    .limit(limit)
                    .collect(toCollection(ArrayList::new));
        }

        return viewModels;
    }

    @GetMapping("/stats")
    public StatsViewModel getStats(
            @RequestParam int year
    ){
        ArrayList<Country> countriesForTheYear =
                data.getCountries().values().stream()
                        .filter(c -> c.getEntries().containsKey(year))
                        .sorted(Comparator.comparing(c -> c.getEntries().get(year)))
                        .collect(toCollection(ArrayList::new));

        Country bestCountry = countriesForTheYear.get(countriesForTheYear.size() - 1);
        Country worstCountry = countriesForTheYear.get(0);

        double averageSchooling = countriesForTheYear.stream()
                .mapToDouble(c -> c.getEntries().get(year)).sum() / countriesForTheYear.size();

        return new StatsViewModel(
                year,
                countriesForTheYear.size(),
                averageSchooling,
                bestCountry.getEntries().get(year),
                worstCountry.getEntries().get(year),
                bestCountry.getEntity(),
                worstCountry.getEntity()
        );
    }

    @GetMapping("/countries/{country}/change")
    public CountryChangeViewModel getStats(
            @PathVariable String country,
            @RequestParam int from,
            @RequestParam int to
    ){
        Country specificCountry = data.getCountries().get(country);

        double fromValue;
        double toValue;

        if(specificCountry.getEntries().containsKey(from)){
            fromValue = specificCountry.getEntries().get(from);
        }else {
            fromValue = 0;
        }

        if(specificCountry.getEntries().containsKey(to)){
            toValue = specificCountry.getEntries().get(to);
        }else {
            toValue = 0;
        }

        double percentage = (toValue - fromValue) / fromValue * 100;

        return new CountryChangeViewModel(
                specificCountry.getEntity(),
                specificCountry.getCode(),
                from,
                to,
                fromValue,
                toValue,
                toValue-fromValue,
                percentage
        );
    }
}
