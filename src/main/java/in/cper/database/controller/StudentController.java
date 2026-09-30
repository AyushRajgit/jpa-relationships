package in.cper.database.controller;

import in.cper.database.entity.Student;
import in.cper.database.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/student")
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
