package co.inventorsoft.academy.demorest.controller;

import co.inventorsoft.academy.demorest.dto.auth.AuthenticationRequest;
import co.inventorsoft.academy.demorest.dto.auth.AuthenticationResponse;
import co.inventorsoft.academy.demorest.service.auth.AuthenticationService;
import co.inventorsoft.academy.demorest.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

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

    @PutMapping("/to-admin/{email}")
    public ResponseEntity<String> setAdmin(@PathVariable String email) {
        authenticationService.setAdmin(email);
        return ResponseEntity.ok("User " + email + " is now an admin.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-endpoint")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("You are an admin!");
    }
}
