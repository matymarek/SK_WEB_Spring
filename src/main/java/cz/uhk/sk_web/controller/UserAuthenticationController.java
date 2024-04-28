package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.forms.ChangePassForm;
import cz.uhk.sk_web.forms.SignupForm;
import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.repository.UserRepository;
import cz.uhk.sk_web.service.LoggedUserProvider;
import cz.uhk.sk_web.util.LogoutHandler;
import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserAuthenticationController {

    private final UserRepository userRepository;
    private final LoggedUserProvider loggedUserProvider;

    public UserAuthenticationController(UserRepository userRepository, LoggedUserProvider loggedUserProvider) {
        this.userRepository = userRepository;
        this.loggedUserProvider = loggedUserProvider;
    }

    @RequestMapping("/login")
    public String login() {
        if (loggedUserProvider.isUserLogged()) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        if (loggedUserProvider.isUserLogged()) {
            return "redirect:/";
        }

        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // validation errors
            return "signup";
        }

        if (!signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
            bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Hesla se neshodují");
            return "signup";
        }

        if (userRepository.findByLogin(signupForm.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.userexists", "Uživatel s tímto loginem již existuje");
            return "signup";
        }

        String passwordHash = (new BCryptPasswordEncoder()).encode(signupForm.getPassword());

        User newUser = new User(
                signupForm.getLogin(),
                passwordHash,
                false
        );

        userRepository.save(newUser);

        return "redirect:/login";
    }

    @RequestMapping(value = "/passChng", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("changepassform") ChangePassForm changePassForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { // validation errors
            model.addAttribute("error", "Build failed");
            System.out.println("Build failed");
            return "redirect:/editWeb";
        }
        if(!new BCryptPasswordEncoder().matches(changePassForm.getPasswordOld(), loggedUserProvider.getLoggedUser().getPassword())){
            model.addAttribute("error", "Nesprávné původní heslo");
            System.out.println("Nespravne puvodni heslo");
            System.out.println(loggedUserProvider.getLoggedUser().getPassword());
            System.out.println(new BCryptPasswordEncoder().encode(changePassForm.getPasswordOld()));
            return "redirect:/editWeb";
        }
        if(!changePassForm.getPassword().equals(changePassForm.getPasswordRep())){
            model.addAttribute("error", "Hesla se neshodují");
            System.out.println("hesla se neshoduji");
            return "redirect:/editWeb";
        }
        String passwordHash = new BCryptPasswordEncoder().encode(changePassForm.getPassword());
        User newUser = userRepository.findById(loggedUserProvider.getLoggedUser().getId()).orElse(null);
        newUser.setPassword(passwordHash);
        userRepository.save(newUser);
        System.out.println("VSE OKA");
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LogoutHandler.logout(request, response);

        return "redirect:/login?logout";
    }
}
