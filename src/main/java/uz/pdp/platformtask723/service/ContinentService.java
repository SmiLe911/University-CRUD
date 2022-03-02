package uz.pdp.platformtask723.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask723.entity.Continent;
import uz.pdp.platformtask723.repository.ContinentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContinentService implements BaseService<Continent, Continent> {

    private final ContinentRepository continentRepository;

    @Override
    public Continent add(Continent continent) {
        return continentRepository.save(continent);
    }

    @Override
    public List<Continent> get() {
        return continentRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);

        if(optionalContinent.isEmpty())
            return false;

        continentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean edit(Integer id, Continent continent) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);

        if(optionalContinent.isEmpty())
            return false;

        Continent editingContinent = optionalContinent.get();
        editingContinent.setName(continent.getName());
        continentRepository.save(editingContinent);

        return true;
    }
}
