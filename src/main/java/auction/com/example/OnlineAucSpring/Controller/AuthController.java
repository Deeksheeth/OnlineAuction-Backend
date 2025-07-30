package auction.com.example.OnlineAucSpring.Controller;

import auction.com.example.OnlineAucSpring.Dtos.AuthRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.AuthResponse;
import auction.com.example.OnlineAucSpring.Dtos.RegisterRequestDTO;
import auction.com.example.OnlineAucSpring.Model.Role;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
//@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO requestDTO) {
        User user = new User();
        user.setUserName(requestDTO.getUserName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRole(requestDTO.getRole() != null ? requestDTO.getRole() : Role.USER);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(), authRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // Get user from DB to access role
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name()));
    }


    @GetMapping("/api/user")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You're authenticated!");
    }

}

