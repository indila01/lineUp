package com.example.demo.config;

import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AuthFilter extends OncePerRequestFilter {


    private JwtProvider jwtProvider;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthFilter(JwtProvider jwtProvider, @Qualifier("userServiceImplement") UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        //Validate the token
        if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            String username = jwtProvider.getUsernameFromToken(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set UsernamePasswordAuthenticationToken object
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }

    //Utility function to get token from request
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer_token = request.getHeader("Authorization");

        if(StringUtils.hasText(bearer_token) && bearer_token.startsWith("Bearer ")) {
            return bearer_token.substring(7); //Remove "Bearer " part
        }
        return bearer_token;
    }
}
