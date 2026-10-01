package in.cper.database.StudentEnrollmentService.controller;

import in.cper.database.StudentEnrollmentService.service.enrollmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enroll")
public class enrollmentController1 {

    private enrollmentServices enrollmentService;

    @Autowired
    public enrollmentController1(enrollmentServices enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<String> saveEnrollment(@RequestParam int studentId, @RequestParam int courseId) {
        String enrollmentMessage = enrollmentService.saveEnrollment(studentId, courseId);

        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentMessage);
    }
}
