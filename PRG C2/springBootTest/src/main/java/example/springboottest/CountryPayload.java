package example.springboottest;

public class CountryPayload {
    String Country;
    int year;
    Double pop;
    String continent;
    Double lifeExp;
    Double gdpPerCap;

    public CountryPayload(String country, int year, Double pop, String continent, Double lifeExp, Double gdpPerCap) {
        Country = country;
        this.year = year;
        this.pop = pop;
        this.continent = continent;
        this.lifeExp = lifeExp;
        this.gdpPerCap = gdpPerCap;
    }

    public String getCountry() {
        return Country;
    }

    public int getYear() {
        return year;
    }

    public Double getPop() {
        return pop;
    }

    public String getContinent() {
        return continent;
    }

    public Double getLifeExp() {
        return lifeExp;
    }

    public Double getGdpPerCap() {
        return gdpPerCap;
    }
}
