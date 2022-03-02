package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.University;
import uz.pdp.platformtask723.payload.UniversityDto;
import uz.pdp.platformtask723.service.UniversityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/university")
public class UniversityController {
    
    private final UniversityService universityService;

    @PostMapping("/add")
    public University add(@RequestBody UniversityDto universityDto) {
        return universityService.add(universityDto);
    }

    @GetMapping("/get")
    public List<University> get() {
        return universityService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return universityService.delete(id) ? "University was deleted" : "University not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        return universityService.edit(id, universityDto) ? "University was edited" : "University not found";
    }
    
}
