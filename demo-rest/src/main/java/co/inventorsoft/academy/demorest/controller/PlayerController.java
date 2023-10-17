package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.dto.PlayerDTO;
import co.inventorsoft.academy.demorest.service.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO create(@RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.create(playerDTO);
    }

    @GetMapping(("/"))
    public List<PlayerDTO> readAll() {
        return playerService.readAllPlayers();
    }

    @GetMapping("/team-categories/{id}")
    public List<PlayerDTO> readByTeamCategoryId(@PathVariable Long id) {
        return playerService.readByCategoryId(id);
    }

    @PutMapping("{/id}")
    public PlayerDTO update(@PathVariable Long id, @RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.update(id, playerDTO);
    }

    @DeleteMapping("{/id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
