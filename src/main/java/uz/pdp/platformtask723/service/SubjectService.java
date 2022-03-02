package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Subject;
import uz.pdp.platformtask723.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<Subject, Subject> {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject add(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> get() {
        return subjectRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isEmpty())
            return false;

        subjectRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isEmpty())
            return false;

        Subject editingSubject = optionalSubject.get();
        editingSubject.setName(subject.getName());
        subjectRepository.save(editingSubject);
        return true;
    }
}
