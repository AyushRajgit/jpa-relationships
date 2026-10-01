package in.cper.database.StudentEnrollmentService.repository;

import in.cper.database.StudentEnrollmentService.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentsRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<StudentEntity> register(List<StudentEntity> students) {
        int batchSize = 0;

        for (int i = 0; i < students.size(); i++) {
            batchSize++;
            entityManager.persist(students.get(i));

            if (batchSize % 5 == 0) {
                entityManager.flush();
                entityManager.clear();
                batchSize = 0;
            }
        }

        return students;
    }

    public StudentEntity getStudent(int id) {
        return entityManager.find(StudentEntity.class, id);
    }

    public List<StudentEntity> getAllStudent() {
        return entityManager
                .createQuery("SELECT s FROM students s", StudentEntity.class)
                .getResultList();
    }

}
