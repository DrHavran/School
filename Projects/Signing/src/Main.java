public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();

        String message = logic.signMessage("message");
        System.out.println(logic.check(message));
    }
}