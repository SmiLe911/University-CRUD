package uz.pdp.platformtask723.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask723.entity.District;
import uz.pdp.platformtask723.payload.DistrictDto;
import uz.pdp.platformtask723.service.DistrictService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {
    
    private final DistrictService districtService;

    @PostMapping("/add")
    public District add(@RequestBody DistrictDto districtDto) {
        return districtService.add(districtDto);
    }

    @GetMapping("/get")
    public List<District> get() {
        return districtService.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return districtService.delete(id) ? "District was deleted" : "District not found";
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestBody DistrictDto districtDto) {
        return districtService.edit(id, districtDto) ? "District was edited" : "District not found";
    }
    
}
