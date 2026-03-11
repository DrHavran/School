import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val oAUTHSecret = "UzNatoSeru:D"
private val encoder = Encode()
private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

class JWTFunctions {
    fun buildToken(id: String, scope: String) : String {
        val header = JSONObject().put("alg", "H256").put("type", "JWT")
        val encodedHeader = encoder.encodeBase64(header.toString())

        val time = LocalDateTime.now().plusWeeks(1).toString()
        val payload = JSONObject()
            .put("id", id)
            .put("scope", scope)
            .put("expiration", time)
        val encodedPayload = encoder.encodeBase64(payload.toString())

        val signature = generateSignature(encodedHeader, encodedPayload)
        val encodedSignature = encoder.encodeBase64(signature)

        val token = "$encodedHeader.$encodedPayload.$encodedSignature"
        println("Generated token: $token")
        return token
    }

    fun checkToken(token: String): Boolean {
        val header = token.split(".")[0]
        val payload = token.split(".")[1]
        val signature = token.split(".")[2]

        if(generateSignature(header, payload) == signature){
            val decodedPayload = JSONObject(encoder.decodeBase64(payload))

            val expiration = decodedPayload.get("expiration").toString()

            val expirationTime = LocalDateTime.parse(expiration, formatter)
            if (LocalDateTime.now().isBefore(expirationTime)) {
                println("Token is valid!")
                return true
            } else {
                println("Token is expired")
                return false
            }
        }else{
            println("Signature does not match")
            return false
        }
    }

    private fun generateSignature(base64Header: String, base64Payload: String): String {
        return encoder.hash("$base64Header.$base64Payload$oAUTHSecret")
    }
}