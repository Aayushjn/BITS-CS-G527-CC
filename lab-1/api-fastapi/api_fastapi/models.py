from pydantic import BaseModel
from pydantic import Field

class Course(BaseModel):
    course_id: int = Field(alias="cid")
    name: str = Field(alias="cname")
    description: str
