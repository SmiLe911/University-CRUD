package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Teacher;
import uz.pdp.platformtask723.payload.TeacherDto;
import uz.pdp.platformtask723.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/add")
    public Teacher add(@RequestBody TeacherDto teacherDto) {
        return teacherService.add(teacherDto);
    }

    @GetMapping("/get")
    public List<Teacher> get() {
        return teacherService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return teacherService.delete(id) ? "Teacher was deleted" : "Teacher not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody TeacherDto teacherDto) {
        return teacherService.edit(id, teacherDto) ? "Teacher was edited" : "Teacher not found";
    }
    
}
