package com.ohgiraffers.blog.jooyeon.controller;


import com.ohgiraffers.blog.jooyeon.model.dto.BlogDTO;
import com.ohgiraffers.blog.jooyeon.service.JooyeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jooyeon")
public class JooyeonController {

    private final JooyeonService jooyeonService;
    private BlogDTO currentBlog;

    @Autowired
    public JooyeonController(JooyeonService jooyeonService) {

        this.jooyeonService = jooyeonService;
    }




    @GetMapping("/blogFirst")
    public String blogfirst() {
        return "/jooyeon/blogFirst";

    }

    @GetMapping("/regist")
    public String regist() {
        return "/jooyeon/regist";

    }

    @GetMapping("/registList")
    public String registList() {
        return "/jooyeon/registList";

    }



    @GetMapping("jypage")
    public String jypage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "/jooyeon/jypage";
    }

    @PostMapping
    public ModelAndView regist(BlogDTO blogDTO, ModelAndView mv) {

        if (blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }
        if (blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }
        int result = jooyeonService.post(blogDTO);


        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jooyeon/registList");
        }

        return mv;
    }


}



