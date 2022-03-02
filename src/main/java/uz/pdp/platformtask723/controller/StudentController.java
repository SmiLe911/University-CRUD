package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Student;
import uz.pdp.platformtask723.payload.StudentDto;
import uz.pdp.platformtask723.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public Student add(@RequestBody StudentDto studentDto) {
        return studentService.add(studentDto);
    }

    @GetMapping("/get")
    public List<Student> get() {
        return studentService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return studentService.delete(id) ? "Student was deleted" : "Student not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
        return studentService.edit(id, studentDto) ? "Student was edited" : "Student not found";
    }
    
}
