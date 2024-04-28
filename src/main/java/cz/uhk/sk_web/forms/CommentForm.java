package cz.uhk.sk_web.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentForm {
    @NotNull
    @Size(min=1, max = 2048)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
