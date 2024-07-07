package com.ohgiraffers.blog.hwayeon.controller;

import com.ohgiraffers.blog.hwayeon.model.entity.HwayeonBlog;
import com.ohgiraffers.blog.hwayeon.service.HwayeonService;
import com.ohgiraffers.blog.hwayeon.model.dto.HwayeonBlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hwayeon")
public class HwayeonController {

    private final HwayeonService hwayeonService;

    @Autowired
    public HwayeonController(HwayeonService hwayeonService) {
        this.hwayeonService = hwayeonService;
    }

    @GetMapping("/main")
    public ModelAndView mainPage() {
        ModelAndView mv = new ModelAndView("hwayeon/main");

        List<HwayeonBlog> latestPosts = hwayeonService.getLatestPosts(3);

        if (!latestPosts.isEmpty()) {
            mv.addObject("latestPosts", latestPosts);
        } else {
            mv.addObject("latestPosts", Collections.emptyList());
        }

        return mv;
    }

    @GetMapping("/editpage")
    public String editpagePage() {
        return "hwayeon/editpage";
    }

    @GetMapping("/detailpost")
    public String detailpost(Model model) {
        Optional<HwayeonBlog> latestPost = hwayeonService.getLatestPost();

        if (latestPost.isPresent()) {

            HwayeonBlogDTO currentBlog = new HwayeonBlogDTO();

            currentBlog.setBlogTitle(latestPost.get().getBlogTitle());
            currentBlog.setBlogContent(latestPost.get().getBlogContent());

            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        } else {
            model.addAttribute("blogTitle", "제목을 찾을 수 없습니다.");
            model.addAttribute("blogContent", "내용을 찾을 수 없습니다.");
        }

        return "hwayeon/detailpost";
    }

    @PostMapping("/detailpost")
    public ModelAndView postBlog(HwayeonBlogDTO hyblogDTO) {
        ModelAndView mv = new ModelAndView();

        if (hyblogDTO.getBlogTitle() == null || hyblogDTO.getBlogTitle().isEmpty() || hyblogDTO.getBlogContent() == null || hyblogDTO.getBlogContent().isEmpty()) {
            mv.setViewName("redirect:/hwayeon/editpage");
            mv.addObject("errorMessage", "제목과 내용을 모두 입력해야 합니다.");
            return mv;
        }

        int result = hwayeonService.post(hyblogDTO);

        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            mv.setViewName("redirect:/hwayeon/detailpost");
        }
        return mv;
    }

    @GetMapping("/modifypage")
    public String modifyPage(Model model) {
        Optional<HwayeonBlog> latestPost = hwayeonService.getLatestPost();
        if (latestPost.isPresent()) {
            model.addAttribute("blogPost", latestPost.get());
        } else {
            model.addAttribute("errorMessage", "수정할 게시글이 없습니다.");
        }
        return "hwayeon/modifypage";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute("blogPost") HwayeonBlog updatedPost) {
        hwayeonService.updatePost(updatedPost);
        return "redirect:/hwayeon/main";
    }
}
