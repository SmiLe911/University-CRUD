package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Region;
import uz.pdp.platformtask723.entity.District;
import uz.pdp.platformtask723.payload.DistrictDto;
import uz.pdp.platformtask723.repository.DistrictRepository;
import uz.pdp.platformtask723.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictService implements BaseService<DistrictDto, District> {
    
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    @Override
    public District add(DistrictDto districtDto) {
        District district = new District();
        district.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if(optionalRegion.isEmpty())
            return null;
        district.setRegion(optionalRegion.get());
        return districtRepository.save(district);
    }

    @Override
    public List<District> get() {
        return districtRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);

        if(optionalDistrict.isEmpty())
            return false;

        districtRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, DistrictDto districtDto) {
        Optional<District> optionalDistrict = districtRepository.findById(id);

        if(optionalDistrict.isEmpty())
            return false;

        District editingDistrict = optionalDistrict.get();
        editingDistrict.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());

        if(optionalRegion.isEmpty())
            return false;

        editingDistrict.setRegion(optionalRegion.get());
        districtRepository.save(editingDistrict);
        return true;

    }
}
