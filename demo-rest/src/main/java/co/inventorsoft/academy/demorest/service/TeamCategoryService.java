package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.entity.TeamCategory;

import java.util.List;

public interface TeamCategoryService {
    List<TeamCategory> readAll();

    TeamCategory readById(Long id);
}
