package cz.uhk.sk_web.util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class RedirectFlashMessageSetter {
    public static final String POST_NOT_FOUND = "Post with provided ID was not found.";
    public static final String USER_NOT_LOGGED_IN = "You are not logged in. Log in for requested content or action.";
    public static final String PAGE_NOT_FOUND = "Page was not found.";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error. Try it later.";
    public static final String FORBIDDEN = "Access to page was forbidden.";
    public static final String UNHANDLED_ERROR = "Unhandled error. Contact support for more.";
    public static final String NOT_ADMIN = "You do not have privileges to this operation.";
    public static final String USER_NOT_FOUND = "User was not found.";
    public static final String ADMIN_SELF_DELETE = "Administrator cannot delete self.";
    private static final String ERROR_KEY = "error";

    public static void setError(RedirectAttributes redirectAttributes, String error) {
        redirectAttributes.addFlashAttribute(ERROR_KEY, error);
    }
}
