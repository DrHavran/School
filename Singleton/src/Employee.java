public class Employee extends Human{
    private int hourlyRate;
    protected int clockedHours;

    public Employee(String name, int age, int hourlyRate, int clockedHours) {
        super(name, age);
        this.hourlyRate = hourlyRate;
        this.clockedHours = clockedHours;
    }

    public int getPaycheck(){
        return hourlyRate*clockedHours;
    }
    public int getClockedHours() {
        return clockedHours;
    }

    public void setClockedHours(int clockedHours) {
        this.clockedHours = clockedHours;
    }
}
