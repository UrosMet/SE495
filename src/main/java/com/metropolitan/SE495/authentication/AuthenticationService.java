package com.metropolitan.SE495.authentication;

import com.metropolitan.SE495.dto.LoginResponse;
import com.metropolitan.SE495.dto.RegisterDTO;
import com.metropolitan.SE495.entity.User;
import com.metropolitan.SE495.jwt.JwtService;
import com.metropolitan.SE495.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.metropolitan.SE495.dto.LoginDTO;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse register(RegisterDTO request){
        var user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String token = jwtService.generateToken(user, generateExtraClaims(user));
        return  new LoginResponse(token);
    }




    public LoginResponse login(LoginDTO authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authToken);
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new LoginResponse(jwt);
    }

    public void logout(String token) {
        tokenBlackListService.blacklistToken(token);
        SecurityContextHolder.clearContext();
    }


    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ID_User" , user.getId());
        extraClaims.put("Full_Name" , user.getFullName());
        extraClaims.put("Email" , user.getEmail());
        extraClaims.put("Role" , user.getRole());
        return extraClaims;
    }
}
