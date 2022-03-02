package uz.pdp.platformtask723.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String street;
    private Integer number;
    private Integer districtId;
}
