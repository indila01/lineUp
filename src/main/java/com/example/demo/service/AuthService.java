package com.example.demo.service;

import com.example.demo.config.JwtProvider;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtProvider jwtProvider;

    @Transactional
    public AuthResponse login(LoginRequest request)  {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        // Find user from system or return error
        User user = userRepository.findUserByUsername(request.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new AuthResponse(token, request.getUsername(), user.getRole().getName());
    }
}
