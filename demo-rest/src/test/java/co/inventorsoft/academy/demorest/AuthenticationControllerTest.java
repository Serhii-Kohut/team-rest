package co.inventorsoft.academy.demorest;

import co.inventorsoft.academy.demorest.dto.auth.AuthenticationRequest;
import co.inventorsoft.academy.demorest.dto.auth.AuthenticationResponse;
import co.inventorsoft.academy.demorest.dto.auth.RegisterRequest;
import co.inventorsoft.academy.demorest.entity.User;
import co.inventorsoft.academy.demorest.entity.enumeration.Role;
import co.inventorsoft.academy.demorest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("User");
        user.setEmail("test@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("New");
        registerRequest.setLastname("User");
        registerRequest.setEmail("new@gmail.com");
        registerRequest.setPassword("password");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testAuthenticate() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("password");

        mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testUserEndpoint() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("password");

        MvcResult result = mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        AuthenticationResponse authResponse = objectMapper.readValue(response, AuthenticationResponse.class);

        mockMvc.perform(get("/auth/user-endpoint")
                        .header("Authorization", "Bearer " + authResponse.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().string("You are authorized!"));
    }

    @Test
    public void testAdminEndpoint() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("password");

        MvcResult result = mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        AuthenticationResponse authResponse = objectMapper.readValue(response, AuthenticationResponse.class);

        mockMvc.perform(get("/auth/admin-endpoint")
                        .header("Authorization", "Bearer " + authResponse.getToken()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSetAdmin() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("password");

        MvcResult result = mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        AuthenticationResponse authResponse = objectMapper.readValue(response, AuthenticationResponse.class);

        mockMvc.perform(put("/auth/to-admin/test@gmail.com")
                        .header("Authorization", "Bearer " + authResponse.getToken()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/auth/admin-endpoint")
                        .header("Authorization", "Bearer " + authResponse.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().string("You are an admin!"));
    }
}