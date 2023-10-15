package co.inventorsoft.academy.demorest.service.impl;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;
import co.inventorsoft.academy.demorest.entity.Player;
import co.inventorsoft.academy.demorest.entity.TeamCategory;
import co.inventorsoft.academy.demorest.repository.PlayerRepository;
import co.inventorsoft.academy.demorest.repository.TeamCategoryRepository;
import co.inventorsoft.academy.demorest.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamCategoryRepository teamCategoryRepository;

    public Player create(PlayerDTO playerDTO) {
        TeamCategory teamCategory = teamCategoryRepository.findById(playerDTO.getTeamCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("TeamCategory with id " + playerDTO.getTeamCategoryId() + " does not exist"));

        return playerRepository.save(Player.builder()
                .playerName(playerDTO.getPlayerName())
                .nationality(playerDTO.getNationality())
                .shirtNumber(playerDTO.getShirtNumber())
                .playerPosition(playerDTO.getPlayerPosition())
                .teamCategory(teamCategory)
                .build());
    }

    public List<Player> readAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> readByCategoryId(Long id) {
        TeamCategory teamCategory = teamCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeamCategory with id " + id + " does not exist"));

        return playerRepository.findPlayersByTeamCategoryId(id);
    }

    public Player update(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Player not found"));
        TeamCategory teamCategory = teamCategoryRepository.findById(playerDTO.getTeamCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("TeamCategory with id " + playerDTO.getTeamCategoryId() + " does not exist"));

        player.setPlayerName(playerDTO.getPlayerName());
        player.setNationality(playerDTO.getNationality());
        player.setShirtNumber(playerDTO.getShirtNumber());
        player.setPlayerPosition(playerDTO.getPlayerPosition());
        player.setTeamCategory(teamCategory);
        return playerRepository.save(player);
    }

    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new NoSuchElementException("Player with id " + id + " does not exist");
        }
        playerRepository.deleteById(id);
    }
}


