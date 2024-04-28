package cz.uhk.sk_web.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class NewImageForm {
    @NotNull
    private MultipartFile image;
    @NotEmpty
    @Size(min = 1, max = 1024)
    private String alt;

    public String getName() {
        return image.getOriginalFilename();
    }

    public MultipartFile getImage() {return image;}

    public void setImage(MultipartFile image) { this.image = image; }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
