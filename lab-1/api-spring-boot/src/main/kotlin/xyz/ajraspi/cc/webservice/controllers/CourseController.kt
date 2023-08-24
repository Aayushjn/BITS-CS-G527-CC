package xyz.ajraspi.cc.webservice.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.ajraspi.cc.webservice.model.Course
import xyz.ajraspi.cc.webservice.store.InMemoryStore

@RestController
class CourseController {
    @GetMapping("/courses", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCourses() = InMemoryStore.getCourses()

    @GetMapping("/courses/{cname}", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE])
    fun getCourseByName(@PathVariable cname: String): ResponseEntity<Any> {
        val course = InMemoryStore.getCourseByName(cname) ?: return ResponseEntity("course with name $cname not found", HttpStatus.NOT_FOUND)
        return ResponseEntity(course, HttpStatus.OK)
    }

    @PostMapping("/courses", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addCourse(@RequestBody course: Course): ResponseEntity<Unit> {
        InMemoryStore.addCourse(course)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/courses")
    fun deleteAllCourses(): ResponseEntity<Unit> {
        InMemoryStore.deleteAllCourses()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/courses/{cid}", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun deleteCourseById(@PathVariable cid: Int) = if (InMemoryStore.deleteCourse(cid)) {
        ResponseEntity(HttpStatus.NO_CONTENT)
    } else {
        ResponseEntity("course with id $cid not found", HttpStatus.NOT_FOUND)
    }

    @PutMapping("/courses/{cid}", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun updateCourseDescription(@PathVariable cid: Int, @RequestBody description: String) =
        if (InMemoryStore.updateCourseDescription(cid, description)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity("course with id $cid not found", HttpStatus.NOT_FOUND)
        }
}