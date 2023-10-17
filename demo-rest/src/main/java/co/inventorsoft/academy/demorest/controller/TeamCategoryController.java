package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.dto.TeamCategoryDTO;
import co.inventorsoft.academy.demorest.service.TeamCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team-categories")
@AllArgsConstructor
public class TeamCategoryController {
    private final TeamCategoryService teamCategoryService;

    @GetMapping
    public List<TeamCategoryDTO> readAllTeamCategory() {
        return teamCategoryService.readAll();
    }

    @GetMapping("/{id}")
    public TeamCategoryDTO readTeamCategoryById(@PathVariable Long id) {
        return teamCategoryService.readById(id);
    }
}