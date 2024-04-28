package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.util.RedirectFlashMessageSetter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.UNHANDLED_ERROR);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.PAGE_NOT_FOUND);
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.FORBIDDEN);
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                RedirectFlashMessageSetter.setError(redirectAttributes, RedirectFlashMessageSetter.INTERNAL_SERVER_ERROR);
                System.out.println(status);
            }
        }
        return "redirect:/";
    }
}

