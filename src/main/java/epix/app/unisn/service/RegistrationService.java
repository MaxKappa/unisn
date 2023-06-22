package epix.app.unisn.service;

import epix.app.unisn.model.User;
import epix.app.unisn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    public void register(User user){
        userService.addNewUser(user);
    }
}
