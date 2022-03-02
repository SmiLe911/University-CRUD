package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Address;
import uz.pdp.platformtask723.entity.District;
import uz.pdp.platformtask723.entity.Group;
import uz.pdp.platformtask723.entity.Student;
import uz.pdp.platformtask723.payload.StudentDto;
import uz.pdp.platformtask723.repository.AddressRepository;
import uz.pdp.platformtask723.repository.DistrictRepository;
import uz.pdp.platformtask723.repository.GroupRepository;
import uz.pdp.platformtask723.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<StudentDto, Student> {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final GroupRepository groupRepository;
    private final DistrictRepository districtRepository;

    @Override
    public Student add(StudentDto studentDto) {
        Address address = new Address();
        address.setStreet(studentDto.getStreet());
        address.setNumber(studentDto.getNumber());

        Optional<District> optionalDistrict = districtRepository.findById(studentDto.getDistrictId());

        if(optionalDistrict.isEmpty())
            return null;

        address.setDistrict(optionalDistrict.get());
        Address savedAddress = addressRepository.save(address);

        Student student = new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setAddress(savedAddress);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());

        if(optionalGroup.isEmpty())
            return null;

        student.setGroup(optionalGroup.get());

        return studentRepository.save(student);
    }

    @Override
    public List<Student> get() {
        return studentRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isEmpty())
            return false;

        Student student = optionalStudent.get();
        Address address = student.getAddress();
        studentRepository.deleteById(id);
        addressRepository.delete(address);
        return true;
    }

    @Override
    public boolean edit(Integer id, StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isEmpty())
            return false;

        Student editingStudent = optionalStudent.get();
        editingStudent.setFirstname(studentDto.getFirstname());
        editingStudent.setLastname(studentDto.getLastname());
        Address editingAddress = editingStudent.getAddress();
        editingAddress.setStreet(studentDto.getStreet());
        editingAddress.setNumber(studentDto.getNumber());
        Optional<District> optionalDistrict = districtRepository.findById(studentDto.getDistrictId());

        if(optionalDistrict.isEmpty())
            return false;

        editingAddress.setDistrict(optionalDistrict.get());
        editingStudent.setAddress(addressRepository.save(editingAddress));

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());

        if(optionalGroup.isEmpty())
            return false;

        editingStudent.setGroup(optionalGroup.get());
        studentRepository.save(editingStudent);

        return true;
    }
}
