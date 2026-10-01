package test.opakovani.models;

public class StatsViewModel {
    private final int year;
    private final int countryCount;
    private final double averageSchoolingYears, highestSchoolingYears, lowestSchoolingYears;
    private final String highestSchoolingCountry, lowestSchoolingCountry;

    public StatsViewModel(int year, int countryCount, double averageSchoolingYears, double highestSchoolingYears, double lowestSchoolingYears, String highestSchoolingCountry, String lowestSchoolingCountry) {
        this.year = year;
        this.countryCount = countryCount;
        this.averageSchoolingYears = averageSchoolingYears;
        this.highestSchoolingYears = highestSchoolingYears;
        this.lowestSchoolingYears = lowestSchoolingYears;
        this.highestSchoolingCountry = highestSchoolingCountry;
        this.lowestSchoolingCountry = lowestSchoolingCountry;
    }

    public int getYear() {
        return year;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public double getAverageSchoolingYears() {
        return averageSchoolingYears;
    }

    public double getLowestSchoolingYears() {
        return lowestSchoolingYears;
    }

    public String getHighestSchoolingCountry() {
        return highestSchoolingCountry;
    }

    public String getLowestSchoolingCountry() {
        return lowestSchoolingCountry;
    }

    public double getHighestSchoolingYears() {
        return highestSchoolingYears;
    }
}
