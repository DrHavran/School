import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val encoder = Encode()

    val allowedSites = HashSet<String>()
    allowedSites.add("nabytek.cz")

    val openCodes = HashMap<String, HashMap<String, String>>()

    embeddedServer(Netty, port = 8000) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(CORS) {
            anyHost()
            allowHost("127.0.0.1:5500", schemes = listOf("http"))
            allowMethod(HttpMethod.Post)
            allowHeader(HttpHeaders.ContentType)
        }

        routing {
            post("/allowedUsers"){
                val message = call.receive<Map<String, String>>()
                if(allowedSites.contains(message["id"])){
                    call.respond(true)
                }else{
                    call.respond(false)
                }
            }
            post("/getCode"){
                val message = call.receive<Map<String, String>>()
                val time = LocalDateTime.now().plusSeconds(30).toString()
                val token = message["id"]+message["redirect_uri"]+message["state"]+message["scope"]+time
                val hash = encoder.hashSHA256(token)
                if (hash != null){
                    val base  = encoder.encodeBase64(hash)
                    call.respondText(base)

                    val map = HashMap<String, String>()
                    message["id"]?.let { it1 -> map["id"] = it1 }
                    message["redirect_uri"]?.let { it1 -> map["redirect_uri"] = it1 }
                    message["state"]?.let { it1 -> map["state"] = it1 }
                    message["scope"]?.let { it1 -> map["scope"] = it1 }
                    map["expiration"] = time

                    openCodes[base] = map
                }
            }
            post("/getToken"){
                val message = call.receive<Map<String, String>>()
                val code = message["code"]
                if(openCodes.contains(code)){
                    val specificMap = openCodes[code]
                    if( message["id"] == specificMap?.get("id") &&
                        message["redirect_uri"] == specificMap?.get("redirect_uri") &&
                        message["state"] == specificMap?.get("state") &&
                        message["scope"] == specificMap?.get("scope"))
                    {
                        val expirationTime = LocalDateTime.parse(specificMap?.get("expiration"), formatter)
                        if (LocalDateTime.now().isBefore(expirationTime)) {
                            println("Token is still valid")
                            openCodes.remove(code)
                        } else {
                            println("Token expired")
                        }
                    }else{
                        println("Info does not match")
                    }
                }else {
                    println("Code isn't registered")
                }
            }
        }
    }.start(wait = true)
}