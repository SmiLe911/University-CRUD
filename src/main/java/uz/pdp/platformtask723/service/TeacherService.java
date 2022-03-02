package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.*;
import uz.pdp.platformtask723.payload.TeacherDto;
import uz.pdp.platformtask723.repository.FacultyRepository;
import uz.pdp.platformtask723.repository.SubjectRepository;
import uz.pdp.platformtask723.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements BaseService<TeacherDto, Teacher> {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public Teacher add(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherDto.getFirstname());
        teacher.setLastname(teacherDto.getLastname());

        Optional<Subject> optionalSubject = subjectRepository.findById(teacherDto.getSubjectId());

        if(optionalSubject.isEmpty())
            return null;

        teacher.setSubject(optionalSubject.get());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(teacherDto.getFacultyId());

        if(optionalFaculty.isEmpty())
            return null;

        teacher.setFaculty(optionalFaculty.get());

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> get() {
        return teacherRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isEmpty())
            return false;

        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, TeacherDto teacherDto) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isEmpty())
            return false;

        Teacher editingTeacher = optionalTeacher.get();
        editingTeacher.setFirstname(teacherDto.getFirstname());
        editingTeacher.setLastname(teacherDto.getLastname());

        Optional<Subject> optionalSubject = subjectRepository.findById(teacherDto.getSubjectId());

        if(optionalSubject.isEmpty())
            return false;

        editingTeacher.setSubject(optionalSubject.get());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(teacherDto.getFacultyId());

        if(optionalFaculty.isEmpty())
            return false;

        editingTeacher.setFaculty(optionalFaculty.get());
        teacherRepository.save(editingTeacher);

        return true;
    }
}
