package in.cper.database.EntityRelationshipService.controller;

import in.cper.database.EntityRelationshipService.entity.Student;
import in.cper.database.EntityRelationshipService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/StudentEntity")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        studentService.create(student);
        return student;
    }

    @GetMapping
    public Set<String> getStudents(@RequestParam int id){
        Set<String> skillSet = studentService.getSkills(id);
        return skillSet;
    }
}
