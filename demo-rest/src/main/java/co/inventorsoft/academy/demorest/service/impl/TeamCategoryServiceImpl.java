package co.inventorsoft.academy.demorest.service.impl;

import co.inventorsoft.academy.demorest.dto.TeamCategoryDTO;
import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.repository.TeamCategoryRepository;
import co.inventorsoft.academy.demorest.service.TeamCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamCategoryServiceImpl implements TeamCategoryService {
    private final TeamCategoryRepository teamCategoryRepository;

    public List<TeamCategoryDTO> readAll() {
        List<TeamCategory> teamCategories = teamCategoryRepository.findAll();
        return teamCategories.stream()
                .map(this::convertTeamCategoryToDto)
                .collect(Collectors.toList());
    }

    public TeamCategoryDTO readById(Long id) {
        TeamCategory teamCategory = teamCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("TeamCategory not found " + id));
        return convertTeamCategoryToDto(teamCategory);
    }

    private TeamCategoryDTO convertTeamCategoryToDto(TeamCategory teamCategory) {
        return TeamCategoryDTO.builder()
                .categoryName(teamCategory.getCategoryName())
                .build();
    }
}
