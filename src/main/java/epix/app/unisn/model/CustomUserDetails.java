package epix.app.unisn.model;

import epix.app.unisn.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final UserRepository userRepository;
    private final String email;
    private final String password;

    public CustomUserDetails(String email, String password, String role, UserRepository userRepository){
        this.email = email;
        this.password = password;
        this.userRepository = userRepository;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        User user = userRepository.findUserByEmail(email).get();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
    }

    @Override
    public String getPassword() {
        return password;
    }

    //Use email instead username
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
