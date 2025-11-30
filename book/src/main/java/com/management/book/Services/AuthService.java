package com.management.book.Services;

import com.management.book.DTO.AuthRequestDTO;
import com.management.book.DTO.AuthResponseDTO;
import com.management.book.Entities.User;
import com.management.book.Repository.UserRepository;
import com.management.book.Security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }




    // Add authentication related methods here
    public AuthResponseDTO login (AuthRequestDTO request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate Password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3. Generate Token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // 4. Return the Response DTO
        return new AuthResponseDTO(token, user.getRole());
    }


}
