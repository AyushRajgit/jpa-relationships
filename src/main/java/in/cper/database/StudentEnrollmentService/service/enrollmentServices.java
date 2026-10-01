package in.cper.database.StudentEnrollmentService.service;

import in.cper.database.StudentEnrollmentService.entity.CourseEntity;
import in.cper.database.StudentEnrollmentService.entity.EnrollmentEntity;
import in.cper.database.StudentEnrollmentService.entity.StudentEntity;
import in.cper.database.StudentEnrollmentService.repository.CourseRepository;
import in.cper.database.StudentEnrollmentService.repository.EnrollmentRepository;
import in.cper.database.StudentEnrollmentService.repository.StudentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class enrollmentServices {

    private StudentsRepository studentRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    public enrollmentServices(StudentsRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public String saveEnrollment(int studentId, int courseId) {
        StudentEntity reqStudent = studentRepository.getStudent(studentId);
        CourseEntity reqCourse = courseRepository.findCourseById(courseId);

        if (reqCourse.getSeatsAvailable() == 0) return "No more seats available for course : " + reqCourse.getCourseName();
        if (reqStudent.getBalance() < reqCourse.getCourseFee()) return "Insufficient balance for course : " + reqCourse.getCourseName();

        reqStudent.setBalance(reqStudent.getBalance() - reqCourse.getCourseFee());

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);

        enrollmentRepository.register(enrollment);
        return "Enrollment successful for studentId = " + studentId + " and courseId = " + courseId;
    }
}
