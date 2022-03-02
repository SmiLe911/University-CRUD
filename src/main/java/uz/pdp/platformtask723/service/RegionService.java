package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Continent;
import uz.pdp.platformtask723.entity.Country;
import uz.pdp.platformtask723.entity.Region;
import uz.pdp.platformtask723.payload.RegionDto;
import uz.pdp.platformtask723.repository.CountryRepository;
import uz.pdp.platformtask723.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService implements BaseService<RegionDto, Region> {
    
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    @Override
    public Region add(RegionDto regionDto) {
        Region region = new Region();
        region.setName(regionDto.getName());
        Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());
        if(optionalCountry.isEmpty())
            return null;
        region.setCountry(optionalCountry.get());
        return regionRepository.save(region);
    }

    @Override
    public List<Region> get() {
        return regionRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);

        if(optionalRegion.isEmpty())
            return false;

        regionRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, RegionDto regionDto) {
        Optional<Region> optionalRegion = regionRepository.findById(id);

        if(optionalRegion.isEmpty())
            return false;

        Region editingRegion = optionalRegion.get();
        editingRegion.setName(regionDto.getName());
        Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());

        if(optionalCountry.isEmpty())
            return false;

        editingRegion.setCountry(optionalCountry.get());
        regionRepository.save(editingRegion);
        return true;

    }
}
