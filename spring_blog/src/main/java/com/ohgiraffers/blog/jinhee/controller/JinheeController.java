package com.ohgiraffers.blog.jinhee.controller;

import com.ohgiraffers.blog.jinhee.model.dto.BlogDTO;
import com.ohgiraffers.blog.jinhee.service.JinheeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jinhee")
public class JinheeController {

    private final JinheeService jinheeService;
    private BlogDTO currentBlog;

    @Autowired
    public JinheeController(JinheeService jinheeService) {
        this.jinheeService = jinheeService;
    }

    @GetMapping("/main")
    public String indexJinhee(){
        return "jinhee/main";
    }

    @GetMapping("/post")
    public String post(){
        return "jinhee/post";
    }



    @GetMapping("/postpage")
    public String postPage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "jinhee/postpage";
    }



    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv){

        if(blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")){
            mv.setViewName("redirect:/jinhee/post");
        }
        if(blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")){
            mv.setViewName("redirect:jinhee/post");
        }

        int result = jinheeService.post(blogDTO);

        if(result <= 0){
            mv.setViewName("error/page");
        }else{
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jinhee/postpage");
        }
        return mv;
    }

    @GetMapping("/review")
    public String share() {
        return "/review";
    }


    }


