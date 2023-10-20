package co.inventorsoft.academy.demorest.dto;

import co.inventorsoft.academy.demorest.entity.enumeration.PlayerPosition;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlayerDTO {
    private Long id;
    @NotBlank
    private String playerName;

    @NotBlank
    private String nationality;

    @NotNull
    @Digits(integer = 2, fraction = 0, message = "Shirt number must be a maximum of 2 digits")
    private Long shirtNumber;

    private PlayerPosition playerPosition;

    private Long teamCategoryId;
}
