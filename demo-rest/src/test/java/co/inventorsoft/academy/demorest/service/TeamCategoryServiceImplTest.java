package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.dto.TeamCategoryDTO;
import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.repository.TeamCategoryRepository;
import co.inventorsoft.academy.demorest.service.impl.TeamCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TeamCategoryServiceImplTest {
    @Mock
    private TeamCategoryRepository teamCategoryRepository;
    @InjectMocks
    private TeamCategoryServiceImpl teamCategoryServiceImpl;

    @Test
    public void readAllTeamCategoriesTest() {
        TeamCategory teamCategory1 = new TeamCategory();
        teamCategory1.setId(1L);
        teamCategory1.setCategoryName("Test_1");

        TeamCategory teamCategory2 = new TeamCategory();
        teamCategory2.setId(2L);
        teamCategory2.setCategoryName("Test_2");

        when(teamCategoryRepository.findAll()).thenReturn(Arrays.asList(teamCategory1, teamCategory2));

        List<TeamCategoryDTO> teamCategoryDTOList = teamCategoryServiceImpl.readAll();

        assertNotNull(teamCategoryDTOList);

        assertEquals(2, teamCategoryDTOList.size());
        assertEquals(teamCategory1.getCategoryName(), teamCategoryDTOList.get(0).getCategoryName());
        assertEquals(teamCategory2.getCategoryName(), teamCategoryDTOList.get(1).getCategoryName());
    }

    @Test
    public void readTeamCategoryByIdTest() {
        TeamCategory teamCategory = new TeamCategory();
        teamCategory.setId(1L);
        teamCategory.setCategoryName("Test Category");

        when(teamCategoryRepository.findById(1L)).thenReturn(Optional.of(teamCategory));

        TeamCategoryDTO teamCategoryDTO = teamCategoryServiceImpl.readById(1L);

        assertNotNull(teamCategoryDTO);
        assertEquals(teamCategory.getCategoryName(), teamCategoryDTO.getCategoryName());
    }
}