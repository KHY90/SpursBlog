package com.ohgiraffers.blog.jinhee.controller;

import com.ohgiraffers.blog.jinhee.model.dto.BlogDTO;
import com.ohgiraffers.blog.jinhee.service.JinheeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public void indexJinhee() {
    }

    @GetMapping("/post")
    public void post() {
    }

    @GetMapping("/journey")
    public String share(Model model) {
        List<BlogDTO> blogs = jinheeService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "jinhee/journey";
    }

    @GetMapping("/postpage")
    public String postPage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogDTO", currentBlog);
            int likeCount = jinheeService.getLikes(currentBlog.getId());
            model.addAttribute("likeCount", likeCount);
        }
        return "jinhee/postpage";
    }

    @GetMapping("/postpage/{id}")
    public String postPage(@PathVariable Long id, Model model) {
        BlogDTO blogDTO = jinheeService.getBlogById(id);
        if (blogDTO != null) {
            model.addAttribute("blogDTO", blogDTO);
            int likeCount = jinheeService.getLikes(id);
            model.addAttribute("likeCount", likeCount);
        }
        return "jinhee/postpage";
    }

    @PostMapping("/postpage/{id}/like")
    public String likePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        jinheeService.likePost(id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/jinhee/postpage/{id}";
    }

    @PostMapping
    public String postBlog(BlogDTO blogDTO) {
        if (blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().isEmpty() ||
                blogDTO.getBlogContent() == null || blogDTO.getBlogContent().isEmpty()) {
            return "redirect:/jinhee/post";
        }

        int result = jinheeService.post(blogDTO);
        if (result <= 0) {
            return "error/page";
        } else {
            currentBlog = blogDTO;
            return "redirect:/jinhee/postpage";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BlogDTO existingBlogDTO = jinheeService.getBlogById(id);
        if (existingBlogDTO == null) {
            return "redirect:/jinhee/journey";
        }
        model.addAttribute("blogDTO", existingBlogDTO);
        return "jinhee/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, @ModelAttribute("blogDTO") BlogDTO blogDTO) {
        jinheeService.updateBlog(blogDTO);
        return "redirect:/jinhee/journey";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, @RequestParam(name = "confirm", defaultValue = "false") boolean confirm, Model model) {
        if (!confirm) {
            BlogDTO blogDTO = jinheeService.getBlogById(id);
            if (blogDTO == null) {
                return "redirect:/jinhee/journey";
            }
            model.addAttribute("blogDTO", blogDTO);
            return "jinhee/delete";
        } else {
            jinheeService.deleteBlogById(id);
            return "redirect:/jinhee/journey";
        }
    }
}
