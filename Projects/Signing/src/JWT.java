import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;

public class JWT {
    public String getToken(String header, String payload) {
        String codedHeader = CodeBase64(header);
        String codedPayload = CodeBase64(payload);

        String signature = CodeBase64(Objects.requireNonNull(hash(codedHeader + "." + codedPayload + Settings.privateKey)));

        return codedHeader+"."+codedPayload+"."+signature;
    }

    public void checkToken(String token) {
        String codedHeader = token.split("\\.")[0];
        String codedPayload = token.split("\\.")[1];

        String checkSignature = CodeBase64(Objects.requireNonNull(hash(codedHeader + "." + codedPayload + Settings.privateKey)));

        if(Objects.equals(token.split("\\.")[2], checkSignature)){
            System.out.println("Header: " + DecodeBase64(codedHeader));
            System.out.println("Payload: " + DecodeBase64(codedPayload));
        }else{
            System.out.println("Token did not match");
        }
    }

    private String CodeBase64(String input){
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.getBytes());
    }

    private String DecodeBase64(String input){
        return new String(Base64.getUrlDecoder().decode(input));
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