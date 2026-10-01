package in.cper.database.StudentEnrollmentService.controller;

import in.cper.database.StudentEnrollmentService.entity.StudentEntity;
import in.cper.database.StudentEnrollmentService.service.studentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register-student")
public class studentController1 {

    private studentServices studentService;

    @Autowired
    public void setStudentService(studentServices studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<List<StudentEntity>> registerCourse(@RequestBody List<StudentEntity> students) {
        List<StudentEntity> registeredStudents = studentService.registerStudent(students);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getCourse(@PathVariable int id) {
        StudentEntity student = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllCourses() {
        List<StudentEntity> studentList = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
}
