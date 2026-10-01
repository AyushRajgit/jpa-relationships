package in.cper.database.StudentEnrollmentService.service;

import in.cper.database.StudentEnrollmentService.entity.StudentEntity;
import in.cper.database.StudentEnrollmentService.repository.StudentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentServices {

    private StudentsRepository studentRepository;

    @Autowired
    public studentServices(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<StudentEntity> registerStudent(List<StudentEntity> students) {
        return studentRepository.register(students);
    }

    public StudentEntity getStudent(int id) {
        return studentRepository.getStudent(id);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.getAllStudent();
    }
}
