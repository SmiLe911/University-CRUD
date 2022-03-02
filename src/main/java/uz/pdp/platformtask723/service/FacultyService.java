package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Faculty;
import uz.pdp.platformtask723.entity.University;
import uz.pdp.platformtask723.payload.FacultyDto;
import uz.pdp.platformtask723.repository.FacultyRepository;
import uz.pdp.platformtask723.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyService implements BaseService<FacultyDto, Faculty> {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Override
    public Faculty add(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        faculty.setName((facultyDto.getName()));
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());

        if(optionalUniversity.isEmpty())
            return null;

        faculty.setUniversity(optionalUniversity.get());

        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> get() {
        return facultyRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);

        if(optionalFaculty.isEmpty())
            return false;

        facultyRepository.deleteById(id);

        return true;
    }

    @Override
    public boolean edit(Integer id, FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);

        if(optionalFaculty.isEmpty())
            return false;

        Faculty faculty = optionalFaculty.get();
        faculty.setName(facultyDto.getName());

        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());

        if(optionalUniversity.isEmpty())
            return false;

        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);

        return true;
    }
}
