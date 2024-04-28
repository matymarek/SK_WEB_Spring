package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.forms.CommentForm;
import cz.uhk.sk_web.model.Comment;
import cz.uhk.sk_web.model.Post;
import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.service.CommentService;
import cz.uhk.sk_web.service.LoggedUserProvider;
import cz.uhk.sk_web.service.PostService;
import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {
    private final LoggedUserProvider loggedUserProvider;
    private final PostService postService;
    private final CommentService commentService;

    public CommentController(LoggedUserProvider loggedUserProvider, PostService postService, CommentService commentService) {
        this.loggedUserProvider = loggedUserProvider;
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/save-comment/post/{postId}", method = RequestMethod.POST)
    public String saveComment(@PathVariable("postId") int postId, @Valid @ModelAttribute("commentForm") CommentForm commentForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/post/" + postId;
        }

        User loggedUser = loggedUserProvider.getLoggedUser();
        Post post = postService.findById(postId);

        if (post == null) {
            RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.POST_NOT_FOUND);
            return "redirect:/";
        }

        Comment newComment = new Comment(post, loggedUser, commentForm.getComment());
        commentService.save(newComment);

        return "redirect:/";
    }
}
