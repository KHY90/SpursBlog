package com.ohgiraffers.blog.jinhee.controller;

import com.ohgiraffers.blog.jinhee.model.dto.BlogDTO;
import com.ohgiraffers.blog.jinhee.service.JinheeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/postpage/{id}")
    public String postPage(@PathVariable Long id, Model model) {
        BlogDTO blogDTO = jinheeService.getBlogById(id);
        if (blogDTO != null) {
            model.addAttribute("blogTitle", blogDTO.getBlogTitle());
            model.addAttribute("blogContent", blogDTO.getBlogContent());
        }
        return "jinhee/postpage";
    }

    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv) {

        if(blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")){
            mv.setViewName("redirect:/jinhee/post");
        }
        if(blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")){
            mv.setViewName("redirect:/jinhee/post");
        }

        int result = jinheeService.post(blogDTO);

        if(result <= 0){
            mv.setViewName("error/page");
        } else {
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jinhee/postpage");
        }
        return mv;
    }

    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model) {
        BlogDTO blogDTO = jinheeService.getBlogById(id);
        if (blogDTO == null) {
            return "redirect:/jinhee/journey";
        }
        model.addAttribute("blogDTO", blogDTO);
        return "jinhee/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSubmit(@ModelAttribute BlogDTO blogDTO) {
        jinheeService.updateBlog(blogDTO);
        return "redirect:/jinhee/journey";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, Model model) {
        BlogDTO blogDTO = jinheeService.getBlogById(id);
        if (blogDTO == null) {
            return "redirect:/jinhee/journey";
        }
        model.addAttribute("blogDTO", blogDTO);
        return "jinhee/delete";
    }

    @GetMapping("/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id) {
        jinheeService.deleteBlogById(id);
        return "redirect:/jinhee/journey";
    }

    @GetMapping("/journey")
    public String share(Model model) {
        List<BlogDTO> blogs = jinheeService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "jinhee/journey";
    }
}
