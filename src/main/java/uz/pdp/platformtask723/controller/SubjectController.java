package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Subject;
import uz.pdp.platformtask723.service.SubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/add")
    public Subject add(@RequestBody Subject subject) {
        return subjectService.add(subject);
    }

    @GetMapping("/get")
    public List<Subject> get() {
        return subjectService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return subjectService.delete(id) ? "Subject was deleted" : "Subject not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Subject subject) {
        return subjectService.edit(id, subject) ? "Subject was edited" : "Subject not found";
    }
    
}
