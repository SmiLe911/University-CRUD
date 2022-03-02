package uz.pdp.platformtask723.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    private String firstname;
    private String lastname;
    private Integer subjectId;
    private Integer facultyId;
}
