public class Main {
    public static void main(String[] args) {

        JWT jwt = new JWT();

        String token = jwt.getToken("META DATA", "PAYLOAD");

        jwt.checkToken(token);
    }
}