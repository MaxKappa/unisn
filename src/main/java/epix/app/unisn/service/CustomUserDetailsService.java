package epix.app.unisn.service;


import epix.app.unisn.model.CustomUserDetails;
import epix.app.unisn.model.User;
import epix.app.unisn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    //Use email instead username
    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email doesn't exist"));
        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRole(), userRepository);
    }


}
