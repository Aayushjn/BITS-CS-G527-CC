package xyz.ajraspi.cc.webservice.course

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xyz.ajraspi.cc.webservice.course.model.Course
import xyz.ajraspi.cc.webservice.store.InMemoryStore

fun Application.courseRoutes() {
    routing {
        route("/courses") {
            get {
                call.respond(InMemoryStore.getCourses())
            }
            get("{cname?}") {
                val courseName = call.parameters["cname"] ?: return@get call.respondText(
                    "missing name",
                    status = HttpStatusCode.BadRequest
                )

                val course = InMemoryStore.getCourseByName(courseName) ?: return@get call.respondText(
                    "course with name $courseName not found",
                    status = HttpStatusCode.NotFound,
                )

                call.respond(course)
            }
            post {
                val course = call.receive<Course>()
                InMemoryStore.addCourse(course)
                call.respond(HttpStatusCode.Created)
            }
            delete {
                InMemoryStore.deleteAllCourses()
                call.respond(HttpStatusCode.NoContent)
            }
            delete("{id?}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "missing id",
                    status = HttpStatusCode.BadRequest
                )

                if (InMemoryStore.deleteCourse(id.toInt())) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respondText("course with id $id not found", status = HttpStatusCode.NotFound)
                }
            }
            put("{id?}") {
                val id = call.parameters["id"] ?: return@put call.respondText(
                    "missing id",
                    status = HttpStatusCode.BadRequest
                )

                if (InMemoryStore.updateCourseDescription(id.toInt(), call.receiveText())) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respondText("course with id $id not found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}
