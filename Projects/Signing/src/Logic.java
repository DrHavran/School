import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

public class Logic {

    public String signMessage(String input) {
        String hash = input+Settings.privateKey;
        hash = hash(hash);
        return input+"."+hash;
    }

    public boolean check(String input){
        String message = input.split("\\.")[0];

        return Objects.equals(signMessage(message), input);
    }

    private String hash(String input) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch (Exception e){
            e.fillInStackTrace();
        }

        return null;
    }
}
