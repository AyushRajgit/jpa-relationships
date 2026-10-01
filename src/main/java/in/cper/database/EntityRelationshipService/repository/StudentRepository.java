package in.cper.database.EntityRelationshipService.repository;

import in.cper.database.EntityRelationshipService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
