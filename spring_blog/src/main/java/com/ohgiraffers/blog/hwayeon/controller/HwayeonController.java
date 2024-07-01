package com.ohgiraffers.blog.hwayeon.controller;

import com.ohgiraffers.blog.hwayeon.service.HwayeonService;
import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hwayeon")
public class HwayeonController {

    private final HwayeonService hwayeonService;
    private BlogDTO currentBlog;

    @Autowired
    public HwayeonController(HwayeonService hwayeonService) {
        this.hwayeonService = hwayeonService;
    }

    @GetMapping("/mainpage")
    public String mainPage() {
        return "hwayeon/mainpage";
    }

    @GetMapping("/postpage")
    public String postPage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "hwayeon/postpage";
    }

    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv) {
        if (blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")) {
            mv.setViewName("redirect:/hwayeon/mainpage");
            return mv;
        }
        if (blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")) {
            mv.setViewName("redirect:/hwayeon/mainpage");
            return mv;
        }

        int result = hwayeonService.post(blogDTO);

        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            currentBlog = blogDTO;
            mv.setViewName("redirect:/hwayeon/postpage");
        }

        return mv;
    }
}
