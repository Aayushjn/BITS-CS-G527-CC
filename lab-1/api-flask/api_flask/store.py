from .models import Course


_courses: list[Course] = []

def get_courses() -> list[Course]:
    return _courses


def get_course_by_name(name: str) -> Course | None:
    for c in _courses:
        if c.name == name:
            return c
    return None


def add_course(course: Course):
    _courses.append(course)


def delete_course(course_id: int) -> bool:
    delete_index = -1
    for i, c in enumerate(_courses):
        if c.course_id == course_id:
            delete_index = i
            break
    
    if delete_index == -1:
        return False
    
    _courses.pop(delete_index)
    return True


def delete_all_courses():
    _courses.clear()


def update_course_description(course_id: int, description: str) -> bool:
    for c in _courses:
        if c.course_id == course_id:
            c.description = description
            return True
    return False
