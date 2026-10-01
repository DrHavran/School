package test.opakovani.models;

public class BestCountryViewModel {
    private String country;
    private String code;
    private int year;
    private double averageYearsOfSchooling;
    private double differenceFromAverageYear;

    public BestCountryViewModel(String country, String code, int year, double averageYearsOfSchooling, double differenceFromAverageYear) {
        this.country = country;
        this.code = code;
        this.year = year;
        this.averageYearsOfSchooling = averageYearsOfSchooling;
        this.differenceFromAverageYear = differenceFromAverageYear;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public int getYear() {
        return year;
    }

    public double getAverageYearsOfSchooling() {
        return averageYearsOfSchooling;
    }

    public double getDifferenceFromAverageYear() {
        return differenceFromAverageYear;
    }
}
