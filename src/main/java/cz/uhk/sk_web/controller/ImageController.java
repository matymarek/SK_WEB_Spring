package cz.uhk.sk_web.controller;

import cz.uhk.sk_web.forms.NewImageForm;
import cz.uhk.sk_web.forms.NewPostForm;
import cz.uhk.sk_web.model.Image;
import cz.uhk.sk_web.model.Post;
import cz.uhk.sk_web.service.ImageService;
import cz.uhk.sk_web.service.LoggedUserProvider;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ImageController {
    private final ImageService imageService;

    private final LoggedUserProvider loggedUserProvider;

    public ImageController(ImageService imageService, LoggedUserProvider loggedUserProvider) {
        this.imageService = imageService;
        this.loggedUserProvider = loggedUserProvider;
    }

    @RequestMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("images", imageService.findAll());
        return "gallery";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("image", imageService.findById(2));
        return "index";
    }

    @RequestMapping(value = "/save-image", method = RequestMethod.POST)
    public String saveImage(@Valid @ModelAttribute("newimageform") NewImageForm newImageForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // validation errors
            return "redirect:/editWeb";
        }
        try {
            String uploadDir = ResourceUtils.getFile("classpath:static/img/").getPath();
            MultipartFile file = newImageForm.getImage();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) { uploadPath.mkdirs(); }
            file.transferTo(new File(uploadDir + File.separator + file.getOriginalFilename()));
            System.out.println(uploadDir+file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/editWeb";
        }
        Image newImage = new Image(
                newImageForm.getName(),
                newImageForm.getAlt()
        );
        imageService.save(newImage);
        return "redirect:/editWeb";
    }
}
