package sakila.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdatedDto extends StoreDto {

    private String updateUsername;
    private int updateAddressId;

}
