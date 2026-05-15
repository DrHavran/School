package example.springboottest;

public class StatsPayload {
    private String continent;
    private int year;
    private int countryCount;
    private Double totalPopulation;
    private Double averageLifeExp;
    private Double averageGdpPerCap;
    private String highestLifeExpCountry;
    private String lowestLifeExpCountry;

    public StatsPayload(String continent, int year, int countryCount, Double totalPopulation, Double averageLifeExp, Double averageGdpPerCap, String highestLifeExpCountry, String lowestLifeExpCountry) {
        this.continent = continent;
        this.year = year;
        this.countryCount = countryCount;
        this.totalPopulation = totalPopulation;
        this.averageLifeExp = averageLifeExp;
        this.averageGdpPerCap = averageGdpPerCap;
        this.highestLifeExpCountry = highestLifeExpCountry;
        this.lowestLifeExpCountry = lowestLifeExpCountry;
    }

    public String getContinent() {
        return continent;
    }

    public int getYear() {
        return year;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public Double getTotalPopulation() {
        return totalPopulation;
    }

    public Double getAverageLifeExp() {
        return averageLifeExp;
    }

    public Double getAverageGdpPerCap() {
        return averageGdpPerCap;
    }

    public String getHighestLifeExpCountry() {
        return highestLifeExpCountry;
    }

    public String getLowestLifeExpCountry() {
        return lowestLifeExpCountry;
    }
}
