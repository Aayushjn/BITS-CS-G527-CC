package xyz.ajraspi.cc.webservice.store

import xyz.ajraspi.cc.webservice.course.model.Course

interface Store {
    fun getCourses(): List<Course>
    fun getCourseByName(name: String): Course?
    fun addCourse(course: Course)
    fun deleteCourse(id: Int): Boolean
    fun deleteAllCourses()
    fun updateCourseDescription(id: Int, description: String): Boolean
}
