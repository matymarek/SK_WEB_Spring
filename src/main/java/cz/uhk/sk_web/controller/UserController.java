package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.forms.ChangePassForm;
import cz.uhk.sk_web.forms.NewImageForm;
import cz.uhk.sk_web.forms.NewPostForm;
import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.service.LoggedUserProvider;
import cz.uhk.sk_web.service.UserService;
import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class UserController {
    private final LoggedUserProvider loggedUserProvider;
    private final UserService userService;

    public UserController(LoggedUserProvider loggedUserProvider, UserService userService) {
        this.loggedUserProvider = loggedUserProvider;
        this.userService = userService;
    }

    @RequestMapping("/editWeb")
    public String editWeb(Model model, RedirectAttributes redirectAttributes) {
        User user = loggedUserProvider.getLoggedUser();
        if(!user.isAdmin()){
            RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.NOT_ADMIN);
            return "redirect:/";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("newpostform", new NewPostForm());
        model.addAttribute("newimageform", new NewImageForm());
        model.addAttribute("changepassform", new ChangePassForm());

        return "editWeb";
    }

    @RequestMapping("/editPass")
    public String profile(Model model) {
        User user = loggedUserProvider.getLoggedUser();
        model.addAttribute("loggedUser", user);
        model.addAttribute("changepassform", new ChangePassForm());
        return "editPass";
    }

    @RequestMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        User user = loggedUserProvider.getLoggedUser();
        var userToDelete = userService.findById(id);

        //user to be deleted does not exist
        if (userToDelete.isEmpty()) {
            RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.USER_NOT_FOUND);
            return "redirect:/";
        }

        //admin can delete everyone
        if (user.isAdmin()) {
            //except himself
            if (Objects.equals(user.getId(), userToDelete.get().getId())) {
                RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.ADMIN_SELF_DELETE);
                return "redirect:/";
            }

            userService.deleteAndArchiveInTransaction(id);
            return "redirect:/";
        }

        //normal user is trying to delete other user
        if (!Objects.equals(user.getId(), userToDelete.get().getId())) {
            RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.NOT_ADMIN);
            return "redirect:/";
        }

        //logged user is deleting his own profile
        userService.deleteAndArchiveInTransaction(id);

        return "redirect:/logout";
    }
}
