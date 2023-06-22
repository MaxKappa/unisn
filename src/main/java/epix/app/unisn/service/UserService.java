package epix.app.unisn.service;

import epix.app.unisn.model.User;
import epix.app.unisn.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User getUserFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        return jwtService.getUserFromJwt(token);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addNewUser(User user){
        assertEmail(user.getEmail());
        checkEmailSyntax(user.getEmail());
        assertUsername(user.getUsername());
        checkStrongPassword(user.getPassword());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }
    public void deleteUser(Long userId) throws UsernameNotFoundException {
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new UsernameNotFoundException("User with id" + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId,String username,String email,String password){
        if (username != null){
            updateUsername(username, userId);
        }
        if (email != null){
            updateEmail(email, userId);
        }
        if (password != null){
            updatePassword(password, userId);
        }
    }

     //Assert email not exists.
    public void assertEmail(String email) throws IllegalStateException{
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
    }
     // Assert username has length >= 4 and not exist
    public void assertUsername(String username) throws IllegalStateException{
        if (username.length() < 4){
            throw new IllegalStateException("Username must be at least 4 characters");
        }
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(userOptional.isPresent()){
            throw new IllegalStateException("Username already used");
        }

    }
    public User searchUserById(Long userId) throws IllegalStateException{
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exists"));
    }
    public void updateUsername(String username, Long userId){
        User user = searchUserById(userId);
        assertUsername(username);
        user.setUsername(username);
    }
    public static void checkEmailSyntax(String emailAddress) throws IllegalStateException {
        if (!Pattern.compile("[a-z0-9._]+@[a-z]+\\.[a-z]{2,3}").matcher(emailAddress).matches()){
            throw new IllegalStateException("Email format is not valid");
        }
    }
    public static void checkStrongPassword(String password) throws IllegalStateException{
        if (!Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,20})").matcher(password).matches()){
            throw new IllegalStateException("Password not enough strong");
        }
    }
    public void updateEmail(String email, Long userId){
        User user = searchUserById(userId);
        checkEmailSyntax(email);
        assertEmail(email);
        user.setEmail(email);
    }

    public void updatePassword(String password, Long userId){
        User user = searchUserById(userId);
        checkStrongPassword(password);
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
    }



}
