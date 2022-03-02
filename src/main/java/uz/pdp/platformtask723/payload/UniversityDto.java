package uz.pdp.platformtask723.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityDto {
    private String name;
    private String street;
    private Integer number;
    private Integer districtId;
}
