import java.security.MessageDigest
import java.util.*

class Encode {
    fun hash(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(input.toByteArray(Charsets.UTF_8))
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes)
    }

    fun encodeBase64(input: String): String {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.toByteArray(Charsets.UTF_8))
    }

    fun decodeBase64(input: String): String {
        return String(Base64.getUrlDecoder().decode(input), Charsets.UTF_8)
    }
}