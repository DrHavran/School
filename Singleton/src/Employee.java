public class Employee extends Human{
    private int hourlyRate;
    protected int clockedHours;

    public Employee(String name, int age, int hourlyRate) {
        super(name, age);
        this.hourlyRate = hourlyRate;
        this.clockedHours = 0;
    }

    public int getPaycheck(){
        System.out.println(hourlyRate*clockedHours);
        return hourlyRate*clockedHours;
    }
    public int getClockedHours() {
        return clockedHours;
    }

    public void setClockedHours(int clockedHours) {
        this.clockedHours = clockedHours;
    }
}
