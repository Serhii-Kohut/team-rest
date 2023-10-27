package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;
import co.inventorsoft.academy.demorest.entity.Player;
import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.repository.PlayerRepository;
import co.inventorsoft.academy.demorest.repository.TeamCategoryRepository;
import co.inventorsoft.academy.demorest.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
public class PlayerServiceImplTest {
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private TeamCategoryRepository teamCategoryRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void createPlayerTest() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerName("Test Player");
        playerDTO.setTeamCategoryId(1L);

        TeamCategory teamCategory = new TeamCategory();
        teamCategory.setId(1L);

        Player player = new Player();
        player.setPlayerName("Test Player");
        player.setTeamCategory(teamCategory);

        when(teamCategoryRepository.findById(1L)).thenReturn(Optional.of(teamCategory));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        PlayerDTO playerDTOResult = playerService.create(playerDTO);

        assertNotNull(playerDTOResult);
        assertEquals(player.getPlayerName(), playerDTOResult.getPlayerName());
    }
}
