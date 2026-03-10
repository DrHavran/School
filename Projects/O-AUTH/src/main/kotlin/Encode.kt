import java.security.MessageDigest
import java.util.*

class Encode {
    fun hashSHA256(input: String): ByteArray? {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(input.toByteArray(Charsets.UTF_8))
    }

    fun encodeBase64(input: ByteArray): String {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input)
    }

    fun decodeBase64(input: String): ByteArray {
        return Base64.getUrlDecoder().decode(input)
    }
}