package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Continent;
import uz.pdp.platformtask723.entity.Country;
import uz.pdp.platformtask723.payload.CountryDto;
import uz.pdp.platformtask723.repository.ContinentRepository;
import uz.pdp.platformtask723.service.CountryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/add")
    public Country add(@RequestBody CountryDto countryDto) {
        return countryService.add(countryDto);
    }

    @GetMapping("/get")
    public List<Country> get() {
        return countryService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return countryService.delete(id) ? "Country was deleted" : "Country not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody CountryDto countryDto) {
        return countryService.edit(id, countryDto) ? "Country was edited" : "Country not found";
    }

}
