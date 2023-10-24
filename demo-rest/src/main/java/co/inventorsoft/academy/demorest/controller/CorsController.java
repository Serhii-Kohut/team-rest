package co.inventorsoft.academy.demorest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cors")
public class CorsController {
    @CrossOrigin(origins = "http://localhost:9091")
    @GetMapping("/localhost-endpoint")
    public ResponseEntity<String> localhostEndpoint() {
        return ResponseEntity.ok("This endpoint is only for localhost");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/public-endpoint")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This endpoint is open to all domains");
    }
}
