package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.service.TeamCategoryService;
import co.inventorsoft.academy.demorest.service.impl.TeamCategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamCategoryController {
    private final TeamCategoryService teamCategoryService;

    @GetMapping
    public ResponseEntity<List<TeamCategory>> readAllTeamCategory() {
        return new ResponseEntity<>(teamCategoryService.readAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamCategory> readTeamCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(teamCategoryService.readById(id), HttpStatus.OK);
    }
}
