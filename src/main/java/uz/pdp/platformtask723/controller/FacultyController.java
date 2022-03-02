package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Faculty;
import uz.pdp.platformtask723.payload.FacultyDto;
import uz.pdp.platformtask723.service.FacultyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {
    
    private final FacultyService facultyService;

    @PostMapping("/add")
    public Faculty add(@RequestBody FacultyDto facultyDto) {
        return facultyService.add(facultyDto);
    }

    @GetMapping("/get")
    public List<Faculty> get() {
        return facultyService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return facultyService.delete(id) ? "Faculty was deleted" : "Faculty not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        return facultyService.edit(id, facultyDto) ? "Faculty was edited" : "Faculty not found";
    }
    
}
