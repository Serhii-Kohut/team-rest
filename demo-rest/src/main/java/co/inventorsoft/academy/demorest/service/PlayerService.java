package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO create(PlayerDTO playerDTO);
    List<PlayerDTO> readAllPlayers();
    List<PlayerDTO> readByCategoryId(Long id);
    PlayerDTO update(Long id, PlayerDTO playerDTO);
    void delete(Long id);
}

