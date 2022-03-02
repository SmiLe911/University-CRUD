package uz.pdp.platformtask723.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String firstname;
    private String lastname;
    private String street;
    private Integer number;
    private Integer districtId;
    private Integer groupId;
}
