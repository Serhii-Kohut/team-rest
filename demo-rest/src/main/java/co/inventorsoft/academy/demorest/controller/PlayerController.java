package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;
import co.inventorsoft.academy.demorest.entity.Player;
import co.inventorsoft.academy.demorest.service.PlayerService;
import co.inventorsoft.academy.demorest.service.impl.PlayerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.create(playerDTO);
    }

    @GetMapping
    public ResponseEntity<List<Player>> readAll() {
        return mappingResponseListOfPlayer(playerService.readAllPlayers());
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<List<Player>> readByTeamCategoryId(@PathVariable Long id) {
        return mappingResponseListOfPlayer(playerService.readByCategoryId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody @Valid PlayerDTO playerDTO) {
        Player updatedPlayer = playerService.update(id, playerDTO);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        playerService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<List<Player>> mappingResponseListOfPlayer(List<Player> playerList) {
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }
}
