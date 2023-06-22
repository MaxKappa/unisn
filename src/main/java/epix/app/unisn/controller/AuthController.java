package epix.app.unisn.controller;

import epix.app.unisn.model.CustomUserDetails;
import epix.app.unisn.service.AuthService;
import epix.app.unisn.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody CustomUserDetails user) {
        try{
            String token = authService.authenticate(user);
            return new ResponseEntity<>(token,  HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


}

