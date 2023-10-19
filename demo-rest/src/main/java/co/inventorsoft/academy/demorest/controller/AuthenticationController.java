package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.auth.AuthenticationRequest;
import co.inventorsoft.academy.demorest.auth.AuthenticationResponse;
import co.inventorsoft.academy.demorest.auth.AuthenticationService;
import co.inventorsoft.academy.demorest.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/user-endpoint")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("You are authorized!");
    }

    @PutMapping("/set-admin/{email}")
    public ResponseEntity<String> setAdmin(@PathVariable String email) {
        authenticationService.setAdmin(email);
        return ResponseEntity.ok("User " + email + " is now an admin.");
    }

    @GetMapping("/admin-endpoint")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("You are an admin!");
    }
}