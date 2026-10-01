package in.cper.database.StudentEnrollmentService.controller;

import in.cper.database.StudentEnrollmentService.entity.CourseEntity;
import in.cper.database.StudentEnrollmentService.service.courseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register-course")
public class courseController1 {

    private courseServices courseService;

    @Autowired
    public courseController1(courseServices courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<List<CourseEntity>> registerCourse(@RequestBody List<CourseEntity> courses) {
        List<CourseEntity> newCourse = courseService.registerCourse(courses);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourse(@PathVariable int id) {
        CourseEntity course = courseService.getCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> courseList = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }
}
