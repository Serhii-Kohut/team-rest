package co.inventorsoft.academy.demorest.service.impl;

import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.repository.TeamCategoryRepository;
import co.inventorsoft.academy.demorest.service.TeamCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamCategoryServiceImpl implements TeamCategoryService {
    private final TeamCategoryRepository teamCategoryRepository;

    public List<TeamCategory> readAll() {
        return teamCategoryRepository.findAll();
    }

    public TeamCategory readById(Long id) {
        return teamCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("TeamCategory not found " + id));
    }
}
