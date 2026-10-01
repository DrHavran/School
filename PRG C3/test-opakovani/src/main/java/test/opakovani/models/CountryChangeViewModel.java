package test.opakovani.models;

public class CountryChangeViewModel {
    private final String country;
    private final String code;
    private final int fromYear, toYear;
    private final double schoolingFrom, schoolingTo, schoolingChange, schoolingChangePercent;

    public CountryChangeViewModel(String country, String code, int fromYear, int toYear, double schoolingFrom, double schoolingTo, double schoolingChange, double schoolingChangePercent) {
        this.country = country;
        this.code = code;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.schoolingFrom = schoolingFrom;
        this.schoolingTo = schoolingTo;
        this.schoolingChange = schoolingChange;
        this.schoolingChangePercent = schoolingChangePercent;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public double getSchoolingFrom() {
        return schoolingFrom;
    }

    public double getSchoolingTo() {
        return schoolingTo;
    }

    public double getSchoolingChange() {
        return schoolingChange;
    }

    public double getSchoolingChangePercent() {
        return schoolingChangePercent;
    }
}
