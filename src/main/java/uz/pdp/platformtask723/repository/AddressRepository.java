package uz.pdp.platformtask723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask723.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
