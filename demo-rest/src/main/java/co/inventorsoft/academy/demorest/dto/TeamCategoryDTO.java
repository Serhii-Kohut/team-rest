package co.inventorsoft.academy.demorest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCategoryDTO {
    @NotNull
    private String categoryName;
}
