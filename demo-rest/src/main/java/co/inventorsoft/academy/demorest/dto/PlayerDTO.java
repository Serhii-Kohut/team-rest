package co.inventorsoft.academy.demorest.dto;

import co.inventorsoft.academy.demorest.entity.PlayerPosition;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PlayerDTO {
    @NotBlank
    private String playerName;

    @NotBlank
    private String nationality;

    @NotNull
    @Digits(integer = 2, fraction = 0, message = "Shirt number must be a maximum of 2 digits")
    private Short shirtNumber;

    private PlayerPosition playerPosition;

    private Long teamCategoryId;
}
