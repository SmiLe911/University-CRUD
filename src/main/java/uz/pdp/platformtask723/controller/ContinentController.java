package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Continent;
import uz.pdp.platformtask723.service.ContinentService;

import java.util.List;

@RestController
@RequestMapping("/continent")
@RequiredArgsConstructor
public class ContinentController {

    private final ContinentService continentService;

    @PostMapping("/add")
    public Continent add(@RequestBody Continent continent) {
        return continentService.add(continent);
    }

    @GetMapping("/get")
    public List<Continent> get() {
        return continentService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return continentService.delete(id) ? "Continent was deleted" : "Continent not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Continent continent) {
        return continentService.edit(id, continent) ? "Continent was edited" : "Continent not found";
    }

}
