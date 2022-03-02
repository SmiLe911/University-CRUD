package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Faculty;
import uz.pdp.platformtask723.entity.Group;
import uz.pdp.platformtask723.payload.GroupDto;
import uz.pdp.platformtask723.repository.FacultyRepository;
import uz.pdp.platformtask723.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements BaseService<GroupDto, Group> {

    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public Group add(GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());

        if(optionalFaculty.isEmpty())
            return null;

        group.setFaculty(optionalFaculty.get());

        return groupRepository.save(group);
    }

    @Override
    public List<Group> get() {
        return groupRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isEmpty())
            return false;

        groupRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, GroupDto groupDto) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isEmpty())
            return false;

        Group group = optionalGroup.get();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());

        if(optionalFaculty.isEmpty())
            return false;

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);

        return true;
    }
}
