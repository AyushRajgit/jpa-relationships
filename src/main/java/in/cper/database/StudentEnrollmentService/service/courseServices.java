package in.cper.database.StudentEnrollmentService.service;

import in.cper.database.StudentEnrollmentService.entity.CourseEntity;
import in.cper.database.StudentEnrollmentService.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class courseServices {

    private CourseRepository courseRepository;

    @Autowired
    public courseServices(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public List<CourseEntity> registerCourse(List<CourseEntity> courses) {
        return courseRepository.register(courses);
    }

    public CourseEntity getCourse(int id) {
        return courseRepository.findCourseById(id);
    }

    public List<CourseEntity> getAllCourses() {
        return new ArrayList<>(courseRepository.findAllCourses());
    }
}
