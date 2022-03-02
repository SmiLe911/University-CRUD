package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Address;
import uz.pdp.platformtask723.payload.AddressDto;
import uz.pdp.platformtask723.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    public Address add(@RequestBody AddressDto addressDto) {
        return addressService.add(addressDto);
    }

    @GetMapping("/get")
    public List<Address> get() {
        return addressService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return addressService.delete(id) ? "Address was deleted" : "Address not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return addressService.edit(id, addressDto) ? "Address was edited" : "Address not found";
    }
}
