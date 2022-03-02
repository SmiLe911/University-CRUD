package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Continent;
import uz.pdp.platformtask723.entity.Country;
import uz.pdp.platformtask723.payload.CountryDto;
import uz.pdp.platformtask723.repository.ContinentRepository;
import uz.pdp.platformtask723.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService implements BaseService<CountryDto, Country> {

    private final CountryRepository countryRepository;
    private final ContinentRepository continentRepository;

    @Override
    public Country add(CountryDto countryDto) {
        Country country = new Country();
        country.setName(countryDto.getName());
        Optional<Continent> optionalContinent = continentRepository.findById(countryDto.getContinentId());
        if(optionalContinent.isEmpty())
            return null;
        country.setContinent(optionalContinent.get());
        return countryRepository.save(country);
    }

    @Override
    public List<Country> get() {
        return countryRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if(optionalCountry.isEmpty())
            return false;

        countryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, CountryDto countryDto) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if(optionalCountry.isEmpty())
            return false;

        Country editingCountry = optionalCountry.get();
        editingCountry.setName(countryDto.getName());

        Optional<Continent> optionalContinent = continentRepository.findById(countryDto.getContinentId());

        if(optionalContinent.isEmpty())
            return false;

        editingCountry.setContinent(optionalContinent.get());
        countryRepository.save(editingCountry);
        return true;

    }
}
