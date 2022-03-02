package uz.pdp.platformtask723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask723.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
