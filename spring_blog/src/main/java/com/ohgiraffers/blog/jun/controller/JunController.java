package com.ohgiraffers.blog.jun.controller;

import com.ohgiraffers.blog.jun.model.dto.BlogDTO;
import com.ohgiraffers.blog.jun.service.JunService;
import com.ohgiraffers.blog.jun.service.JunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jun")
public class JunController {

    private final JunService junService;
    private BlogDTO currentBlog;

    @Autowired
    public JunController(JunService junService) {
        this.junService = junService;
    }

    // GET 요청을 처리해서 "jun/page" 뷰를 반환
    @GetMapping
    public String indexJUN() {
        return "jun/page";
    }


    @GetMapping("/junpost")
    public String postPage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "jun/junpost";
    }



    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv){

        if(blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")){
            mv.setViewName("redirect:/jinhee/post");
        }
        if(blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")){
            mv.setViewName("redirect:jinhee/post");
        }

        int result = junService.post(blogDTO);

        if(result <= 0){
            mv.setViewName("error/page");
        }else{
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jun/junpost");
        }
        return mv;
    }

    @GetMapping("/review")
    public String share() {
        return "/review";
    }


}


