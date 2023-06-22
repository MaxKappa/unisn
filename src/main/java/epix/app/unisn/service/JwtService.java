package epix.app.unisn.service;

import epix.app.unisn.model.CustomUserDetails;
import epix.app.unisn.model.User;
import epix.app.unisn.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;
    @Value("${epix.app.jwtSecret}")
    private String jwtSecret;

    @Value("${epix.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getUsernameFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public User getUserFromJwt(String token){
        String email = getUsernameFromJwt(token);
        Optional<User> user = userRepository.findUserByEmail(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Invalid token");
        }
        return user.get();
    }

    public String generateToken (CustomUserDetails userDetails) {
        return "Bearer " + Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromJwt(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return false;
    }

    public boolean validateJwt(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}

