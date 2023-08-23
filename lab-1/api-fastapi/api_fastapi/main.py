from fastapi import FastAPI
from fastapi import Request
from fastapi import Response
from fastapi import status
from fastapi.responses import PlainTextResponse

from . import store
from .models import Course

app = FastAPI()

@app.get("/courses", response_model=list[Course])
async def get_courses():
    return store.get_courses()


@app.get("/courses/{cname}", response_model=Course | None)
async def get_course_by_name(cname: str):
    course = store.get_course_by_name(cname)
    if course is None:
        return PlainTextResponse(f"course with name {cname} not found", status_code=status.HTTP_404_NOT_FOUND)

    return course

@app.post("/courses", status_code=status.HTTP_201_CREATED)
async def create_course(course: Course):
    store.add_course(course)


@app.delete("/courses", status_code=status.HTTP_204_NO_CONTENT)
async def delete_all_courses():
    store.delete_all_courses()


@app.delete("/courses/{cid}")
async def delete_course(cid: int):
    if store.delete_course(cid):
        return Response(status_code=status.HTTP_204_NO_CONTENT)
    return PlainTextResponse(f"course with id {cid} not found", status_code=status.HTTP_404_NOT_FOUND)


@app.put("/courses/{cid}", response_class=Response)
async def update_course_description(cid: int, request: Request):
    description = await request.body()
    if store.update_course_description(cid, description):
        return Response(status_code=status.HTTP_204_NO_CONTENT)
    return PlainTextResponse(f"course with id {cid} not found", status_code=status.HTTP_404_NOT_FOUND)
