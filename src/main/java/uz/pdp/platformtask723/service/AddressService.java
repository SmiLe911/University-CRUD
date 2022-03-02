package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Address;
import uz.pdp.platformtask723.entity.District;
import uz.pdp.platformtask723.payload.AddressDto;
import uz.pdp.platformtask723.repository.AddressRepository;
import uz.pdp.platformtask723.repository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements BaseService<AddressDto, Address> {

    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;

    @Override
    public Address add(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        if(optionalDistrict.isEmpty())
            return null;
        address.setDistrict(optionalDistrict.get());
        return addressRepository.save(address);
    }

    @Override
    public List<Address> get() {
        return addressRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty())
            return false;

        addressRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty())
            return false;

        Address editingAddress = optionalAddress.get();
        editingAddress.setStreet(addressDto.getStreet());
        editingAddress.setNumber(addressDto.getNumber());
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());

        if(optionalDistrict.isEmpty())
            return false;

        editingAddress.setDistrict(optionalDistrict.get());
        addressRepository.save(editingAddress);
        return true;
    }
}
