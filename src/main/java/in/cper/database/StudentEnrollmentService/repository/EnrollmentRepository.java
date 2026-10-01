package in.cper.database.StudentEnrollmentService.repository;

import in.cper.database.StudentEnrollmentService.entity.EnrollmentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void register(EnrollmentEntity enrollment) {
        entityManager.persist(enrollment);
    }
}
