public class Manager {
    private static Manager instance;
    public static Manager getInstance() {
        if(instance == null) {
            instance = new Manager();
        }

        return instance;
    }
}
