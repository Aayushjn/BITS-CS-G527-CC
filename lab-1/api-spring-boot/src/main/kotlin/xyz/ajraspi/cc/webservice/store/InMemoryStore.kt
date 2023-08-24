package xyz.ajraspi.cc.webservice.store

import xyz.ajraspi.cc.webservice.model.Course

object InMemoryStore : Store {
    private val courses: MutableList<Course> = mutableListOf()

    override fun getCourses(): List<Course> = courses

    override fun getCourseByName(name: String): Course? = courses.find { it.name == name }

    override fun addCourse(course: Course) {
        courses.add(course)
    }

    override fun deleteCourse(id: Int): Boolean = courses.removeIf { it.id == id }

    override fun deleteAllCourses() = courses.clear()

    override fun updateCourseDescription(id: Int, description: String): Boolean {
        for ((i, course) in courses.withIndex()) {
            if (course.id == id) {
                courses[i] = course.copy(description = description)
                return true
            }
        }
        return false
    }
}
