import java.util.ArrayList;

public class Manager {
    private static Manager instance;
    private ArrayList<Employee> employees;
    private int day;

    public void addPerson(Employee employee){
        employees.add(employee);
    }

    public void MAKETHEMWORK(){
        day++;
        for(Employee employee : employees){
            employee.clockedHours += 8;
            if(day==30){
                employee.getPaycheck();
            }
        }
        if(day==30){
            day=0;
        }
    }

    private Manager() {
        employees = new ArrayList<>();
        day = 0;
    }
    public static Manager getInstance() {
        if(instance == null) {
            instance = new Manager();
        }
        return instance;
    }
}
