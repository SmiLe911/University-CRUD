package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.Region;
import uz.pdp.platformtask723.payload.RegionDto;
import uz.pdp.platformtask723.service.RegionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
    
    private final RegionService regionService;

    @PostMapping("/add")
    public Region add(@RequestBody RegionDto regionDto) {
        return regionService.add(regionDto);
    }

    @GetMapping("/get")
    public List<Region> get() {
        return regionService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return regionService.delete(id) ? "Region was deleted" : "Region not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody RegionDto regionDto) {
        return regionService.edit(id, regionDto) ? "Region was edited" : "Region not found";
    }
}
