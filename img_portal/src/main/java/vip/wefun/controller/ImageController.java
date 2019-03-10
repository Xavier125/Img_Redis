package vip.wefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vip.wefun.service.ImageService;

@Controller
public class ImageController {

    @Autowired
    ImageService imageServiceImpl;


    @GetMapping("show")
    public String show(Model model) {
        model.addAttribute( "imageList", imageServiceImpl.show() );
        return "index";
    }
}
