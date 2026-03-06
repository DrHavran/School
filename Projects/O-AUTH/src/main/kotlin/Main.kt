import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*

fun main() {
    embeddedServer(Netty, port = 8000) {
        install(CORS) {
            anyHost()
        }

        routing {
            get("/") {
                call.respondText("You made connection")
            }
            post("/"){
                println(call.receive<String>())
                println("Someone send smth")
                call.respondText("Server got your message!")
            }
        }
    }.start(wait = true)
}