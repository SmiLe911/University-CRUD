package uz.pdp.platformtask723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask723.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
