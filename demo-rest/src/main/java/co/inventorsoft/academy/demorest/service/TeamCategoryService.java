package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.dto.TeamCategoryDTO;

import java.util.List;

public interface TeamCategoryService {
    List<TeamCategoryDTO> readAll();

    TeamCategoryDTO readById(Long id);
}
