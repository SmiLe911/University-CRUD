package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Address;
import uz.pdp.platformtask723.entity.District;
import uz.pdp.platformtask723.entity.University;
import uz.pdp.platformtask723.payload.UniversityDto;
import uz.pdp.platformtask723.repository.AddressRepository;
import uz.pdp.platformtask723.repository.DistrictRepository;
import uz.pdp.platformtask723.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService implements BaseService<UniversityDto, University> {

    private final UniversityRepository universityRepository;
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;

    @Override
    public University add(UniversityDto universityDto) {
        Address address = new Address();
        address.setStreet(universityDto.getStreet());
        address.setNumber(universityDto.getNumber());
        Optional<District> optionalDistrict = districtRepository.findById(universityDto.getDistrictId());
        if(optionalDistrict.isEmpty())
            return null;
        address.setDistrict(optionalDistrict.get());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        return universityRepository.save(university);
    }

    @Override
    public List<University> get() {
        return universityRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);

        if(optionalUniversity.isEmpty())
            return false;
        University university = optionalUniversity.get();
        Address address = university.getAddress();
        universityRepository.deleteById(id);
        addressRepository.delete(address);
        return true;
    }

    @Override
    public boolean edit(Integer id, UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);

        if(optionalUniversity.isEmpty())
            return false;

        University editingUniversity = optionalUniversity.get();
        editingUniversity.setName(universityDto.getName());

        Address editingAddress = editingUniversity.getAddress();
        editingAddress.setStreet(universityDto.getStreet());
        editingAddress.setNumber(universityDto.getNumber());
        Optional<District> optionalDistrict = districtRepository.findById(universityDto.getDistrictId());

        if(optionalDistrict.isEmpty())
            return false;

        editingAddress.setDistrict(optionalDistrict.get());
        editingUniversity.setAddress(addressRepository.save(editingAddress));
        universityRepository.save(editingUniversity);

        return true;
    }
}
