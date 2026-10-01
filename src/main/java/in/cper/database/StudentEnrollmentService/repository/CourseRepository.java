package in.cper.database.StudentEnrollmentService.repository;

import in.cper.database.StudentEnrollmentService.entity.CourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CourseEntity> register(List<CourseEntity> courses) {
        int batchSize = 0;

        for (int i = 0; i < courses.size(); i++) {
            batchSize++;
            entityManager.persist(courses.get(i));

            if (batchSize % 5 == 0) {
                entityManager.flush();
                entityManager.clear();
                batchSize = 0;
            }
        }

        return courses;
    }

    public CourseEntity findCourseById(int id) {
        return entityManager.find(CourseEntity.class, id);
    }

    public List<CourseEntity> findAllCourses() {
        return entityManager
                .createQuery("SELECT c FROM courses c", CourseEntity.class)
                .getResultList();
    }
}
