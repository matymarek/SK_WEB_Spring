package cz.uhk.sk_web.service;

import cz.uhk.sk_web.exception.CustomAuthenticationException;
import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.repository.UserRepository;
import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoggedUserProvider {
    private static final String ERROR_MESSAGE = RedirectFlashMessageSetter.USER_NOT_LOGGED_IN;
    private final UserRepository userRepository;

    public LoggedUserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public @NotNull User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication != null && authentication.isAuthenticated())) {
            throw new CustomAuthenticationException(ERROR_MESSAGE);
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new CustomAuthenticationException(ERROR_MESSAGE);
        }

        User user = userRepository.findByLogin(((UserDetails) principal).getUsername());

        if (user == null) {
            throw new CustomAuthenticationException(ERROR_MESSAGE);
        }

        return user;
    }

    public Optional<User> getLoggedUserOptional() {
        try {
            return Optional.of(getLoggedUser());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean isUserLogged() {
        try {
            getLoggedUser();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
