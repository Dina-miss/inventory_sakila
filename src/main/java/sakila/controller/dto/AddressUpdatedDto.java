package sakila.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdatedDto extends AddressDto {

    private String updateAddress;
    private String updateAddress2;
    private String updateDistrict;
    private String updateCity;
    private String updateCountry;
    private String updatePostalCode;
    private String updatePhone;

}
