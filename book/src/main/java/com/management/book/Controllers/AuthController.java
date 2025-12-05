package com.management.book.Controllers;


import com.management.book.DTO.AuthRequestDTO;
import com.management.book.DTO.AuthResponseDTO;
import com.management.book.DTO.LogoutResponseDTO;
import com.management.book.Entities.User;
import com.management.book.Repository.UserRepository;
import com.management.book.Security.JwtFilter;
import com.management.book.Security.JwtUtil;
import com.management.book.Services.AuthService;
import com.management.book.Services.TokenBlackListService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private AuthService authService;
    private TokenBlackListService tokenBlackListService;

    public AuthController(AuthService authService, TokenBlackListService tokenBlackListService) {
        this.authService = authService;
        this.tokenBlackListService = tokenBlackListService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        // Delegate the work to the service
        AuthResponseDTO response = authService.login(request);

        // Return the result
        return ResponseEntity.ok(response);
    }

    //logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(@RequestHeader("Authorization") String authHeader) {

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer "
            tokenBlackListService.blacklistToken(token);

            return ResponseEntity.ok(new LogoutResponseDTO("Logged out successfully", true));
        }

        return ResponseEntity.badRequest().body(new LogoutResponseDTO("Invalid Token format", false));
    }
}
