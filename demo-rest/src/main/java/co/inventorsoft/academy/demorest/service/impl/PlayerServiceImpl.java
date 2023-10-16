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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamCategoryRepository teamCategoryRepository;

    public PlayerDTO create(PlayerDTO playerDTO) {
        TeamCategory teamCategory = teamCategoryRepository.findById(playerDTO.getTeamCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("TeamCategory with id " + playerDTO.getTeamCategoryId() + " does not exist"));

        Player player = playerRepository.save(Player.builder()
                .playerName(playerDTO.getPlayerName())
                .nationality(playerDTO.getNationality())
                .shirtNumber(playerDTO.getShirtNumber())
                .playerPosition(playerDTO.getPlayerPosition())
                .teamCategory(teamCategory)
                .build());

        return convertPlayerToDto(player);
    }

    public List<PlayerDTO> readAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(this::convertPlayerToDto)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> readByCategoryId(Long id) {
        List<Player> players = playerRepository.findPlayersByTeamCategoryId(id);
        return players.stream()
                .map(this::convertPlayerToDto)
                .collect(Collectors.toList());
    }

    public PlayerDTO update(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Player not found"));
        TeamCategory teamCategory = teamCategoryRepository.findById(playerDTO.getTeamCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("TeamCategory with id " + playerDTO.getTeamCategoryId() + " does not exist"));

        Player updatedPlayer = Player.builder()
                .id(player.getId())
                .playerName(playerDTO.getPlayerName())
                .nationality(playerDTO.getNationality())
                .shirtNumber(playerDTO.getShirtNumber())
                .playerPosition(playerDTO.getPlayerPosition())
                .teamCategory(teamCategory)
                .build();

        playerRepository.save(updatedPlayer);
        return convertPlayerToDto(updatedPlayer);
    }


    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    private PlayerDTO convertPlayerToDto(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .playerName(player.getPlayerName())
                .nationality(player.getNationality())
                .shirtNumber(player.getShirtNumber())
                .playerPosition(player.getPlayerPosition())
                .teamCategoryId(player.getTeamCategory().getId())
                .build();
    }
}


