package in.cper.database.service;

import in.cper.database.entity.Student;
import in.cper.database.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String create(Student student) {
        studentRepository.save(student);
        return "success";
    }

    public Set<String> getSkills(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student.getSkills();
    }
}
