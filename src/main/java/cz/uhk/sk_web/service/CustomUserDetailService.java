package cz.uhk.sk_web.service;

import cz.uhk.sk_web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userRepository) {
        this.userService = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User currentUser = userService.findByLogin(login);

        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder();
        builder.username(currentUser.getLogin()); //there must be unique value
        builder.password(currentUser.getPassword());
        builder.roles(currentUser.isAdmin() ? "ADMIN" : "USER");

        return builder.build();
    }

}