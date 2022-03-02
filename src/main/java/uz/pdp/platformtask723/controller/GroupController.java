package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Group;
import uz.pdp.platformtask723.payload.GroupDto;
import uz.pdp.platformtask723.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/add")
    public Group add(@RequestBody GroupDto groupDto) {
        return groupService.add(groupDto);
    }

    @GetMapping("/get")
    public List<Group> get() {
        return groupService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return groupService.delete(id) ? "Group was deleted" : "Group not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        return groupService.edit(id, groupDto) ? "Group was edited" : "Group not found";
    }
    
}
