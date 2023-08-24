from dataclasses import dataclass

@dataclass
class Course:
    course_id: int
    name: str
    description: str

    def as_dict(self) -> dict[str, int | str]:
        return {
            "cid": self.course_id,
            "cname": self.name,
            "description": self.description,
        }
    
    @classmethod
    def from_dict(cls, data: dict[str, int | str]):
        if "cid" not in data or "cname" not in data or "description" not in data:
            raise ValueError("missing mandatory fields")
        return cls(course_id=data["cid"], name=data["cname"], description=data["description"])
