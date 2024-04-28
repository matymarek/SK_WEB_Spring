package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.forms.NewPostForm;
import cz.uhk.sk_web.model.Post;
import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.service.LoggedUserProvider;
import cz.uhk.sk_web.service.PostService;
import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
public class PostController {
    private final PostService postService;
    private final LoggedUserProvider loggedUserProvider;

    public PostController(PostService postService, LoggedUserProvider loggedUserProvider) {
        this.postService = postService;
        this.loggedUserProvider = loggedUserProvider;
    }

    @RequestMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postService.findAllReversed());
        return "posts";
    }

    @RequestMapping(value = "/save-post", method = RequestMethod.POST)
    public String savePost(@Valid @ModelAttribute("newpostform") NewPostForm newPostForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // validation errors
            return "redirect:/editWeb";
        }
        Post newPost = new Post(
                loggedUserProvider.getLoggedUser(),
                newPostForm.getTitle(),
                newPostForm.getContent()
        );
        postService.save(newPost);
        return "redirect:/";
    }

    @RequestMapping("/delete-post/{id}")
    public String deletePost(@PathVariable int id, RedirectAttributes redirectAttributes) {
        User user = loggedUserProvider.getLoggedUser();
        Post post = postService.findById(id);
        if (post == null) {
            RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.POST_NOT_FOUND);
            return "redirect:/";
        }
        if (user != null) {
            postService.delete(post);
            return "redirect:/";
        }
        RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.USER_NOT_LOGGED_IN);
        return "redirect:/";
    }

}
