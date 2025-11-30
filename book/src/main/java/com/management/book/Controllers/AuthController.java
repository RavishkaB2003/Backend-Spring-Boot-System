package com.management.book.Controllers;


import com.management.book.DTO.AuthRequestDTO;
import com.management.book.DTO.AuthResponseDTO;
import com.management.book.Entities.User;
import com.management.book.Repository.UserRepository;
import com.management.book.Security.JwtFilter;
import com.management.book.Security.JwtUtil;
import com.management.book.Services.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        // Delegate the work to the service
        AuthResponseDTO response = authService.login(request);

        // Return the result
        return ResponseEntity.ok(response);
    }
}
