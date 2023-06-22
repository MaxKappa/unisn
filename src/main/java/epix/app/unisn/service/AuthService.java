package epix.app.unisn.service;

import epix.app.unisn.model.CustomUserDetails;
import epix.app.unisn.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(CustomUserDetails request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = customUserDetailsService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(user);
    }

    private void revokeToken(User user) {

    }

}

