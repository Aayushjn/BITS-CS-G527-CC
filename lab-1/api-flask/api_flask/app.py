from flask import Flask
from flask import make_response
from flask import request

from .models import Course
from . import store


app = Flask(__name__)


@app.get("/courses")
def get_courses():
    return [c.as_dict() for c in store.get_courses()]


@app.get("/courses/<string:cname>")
def get_course_by_name(cname: str):
    course = store.get_course_by_name(cname)
    if course is None:
        return make_response(f"course with name {cname} not found", 404)

    return course.as_dict()

@app.post("/courses")
def create_course():
    course = Course.from_dict(request.get_json())
    store.add_course(course)
    resp = make_response()
    resp.status_code = 201
    return resp


@app.delete("/courses")
def delete_all_courses():
    store.delete_all_courses()
    resp = make_response()
    resp.status_code = 204
    return resp


@app.delete("/courses/<int:cid>")
def delete_course(cid: int):
    if store.delete_course(cid):
        resp = make_response()
        resp.status_code = 204
        return resp
    
    return make_response(f"course with id {cid} not found", 404)


@app.put("/courses/<int:cid>")
def update_course_description(cid: int):
    description = request.get_data(as_text=True)
    if store.update_course_description(cid, description):
        resp = make_response()
        resp.status_code = 204
        return resp
    
    return make_response(f"course with id {cid} not found", 404)

