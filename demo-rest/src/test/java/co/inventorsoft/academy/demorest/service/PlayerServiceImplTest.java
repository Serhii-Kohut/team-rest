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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    @Test
    public void readAllPlayersTest() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setPlayerName("Test 1");
        player1.setTeamCategory(new TeamCategory(1L, "Test1"));

        Player player2 = new Player();
        player2.setId(2L);
        player2.setPlayerName("Test 2");
        player2.setTeamCategory(new TeamCategory(2L, "Test2"));

        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        List<PlayerDTO> playerDTOList = playerService.readAllPlayers();

        assertNotNull(playerDTOList);

        assertEquals(2, playerDTOList.size());
        assertEquals(player1.getPlayerName(), playerDTOList.get(0).getPlayerName());
        assertEquals(player1.getTeamCategory().getId(), playerDTOList.get(0).getTeamCategoryId());

        assertEquals(player2.getPlayerName(), playerDTOList.get(1).getPlayerName());
        assertEquals(player2.getTeamCategory().getId(), playerDTOList.get(1).getTeamCategoryId());
    }

    @Test
    public void readPlayersByCategoryIdTest() {
        Player player = new Player();
        player.setId(1L);
        player.setPlayerName("TestName");
        player.setTeamCategory(new TeamCategory(1L, "Test"));

        when(playerRepository.findPlayersByTeamCategoryId(1L)).thenReturn(Collections.singletonList(player));

        List<PlayerDTO> playerDTOList = playerService.readByCategoryId(1L);

        assertNotNull(playerDTOList);

        assertEquals(1, playerDTOList.size());
        assertEquals(player.getPlayerName(), playerDTOList.get(0).getPlayerName());
        assertEquals(player.getTeamCategory().getId(), playerDTOList.get(0).getTeamCategoryId());
    }
}
