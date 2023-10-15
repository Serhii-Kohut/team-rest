package co.inventorsoft.academy.demorest.service;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;
import co.inventorsoft.academy.demorest.entity.Player;

import java.util.List;

public interface PlayerService {
    Player create(PlayerDTO playerDTO);
    List<Player> readAllPlayers();
    List<Player> readByCategoryId(Long id);
    Player update(Long id, PlayerDTO playerDTO);
    void delete(Long id);
}

