package example.springboottest;

public class LifeExpPayload {
    String country;
    String continent;
    int fromYear;
    int toYear;
    Double lifeExpFrom;
    Double lifeExpTo;
    Double improvement;

    public LifeExpPayload(String country, String continent, int fromYear, int toYear, Double lifeExpFrom, Double lifeExpTo, Double improvement) {
        this.country = country;
        this.continent = continent;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.lifeExpFrom = lifeExpFrom;
        this.lifeExpTo = lifeExpTo;
        this.improvement = improvement;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() {
        return continent;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public Double getLifeExpFrom() {
        return lifeExpFrom;
    }

    public Double getLifeExpTo() {
        return lifeExpTo;
    }

    public Double getImprovement() {
        return improvement;
    }
}
