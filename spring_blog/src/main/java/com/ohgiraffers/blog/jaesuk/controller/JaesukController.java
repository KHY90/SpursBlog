package com.ohgiraffers.blog.jaesuk.controller;

<<<<<<< Updated upstream
// BlogDTO 클래스를 가져옵니다.
import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// GetMapping과 PostMapping을 가져옵니다.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 이 클래스는 컨트롤러로 동작합니다.
@Controller

// "/jaesuk"으로 시작하는 URL 요청을 처리합니다.
=======
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import com.ohgiraffers.blog.jaesuk.repository.JaesukRepository;
import com.ohgiraffers.blog.jaesuk.service.JaesukService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
>>>>>>> Stashed changes
@RequestMapping("/jaesuk")
public class JaesukController {

<<<<<<< Updated upstream
    // JaesukService라는 서비스를 사용합니다.
    private final com.ohgiraffers.blog.jaesuk.service.JaesukService JaesukService;

    // 생성자를 통해 JaesukService를 주입받습니다.
    @Autowired
    public JaesukController(com.ohgiraffers.blog.jaesuk.service.JaesukService jaesukService) {
        this.JaesukService = jaesukService;
    } // 서비스 경로 수정

    // GET 요청이 들어오면 "jaesuk/page" 페이지를 반환합니다.
    @GetMapping
    public String indexJaesuk(){
        return "jaesuk/page";
    }

    // POST 요청이 들어오면 블로그 글을 처리합니다.
    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv){

        // 블로그 제목이 없으면 "jaesuk" 페이지로 리디렉션합니다.
        if(blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")){
            mv.setViewName("redirect:jaesuk");
        }
        // 블로그 내용이 없으면 "jaesuk" 페이지로 리디렉션합니다.
        if(blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")){
            mv.setViewName("redirect:jaesuk");
        }

        // 블로그 글을 저장하고 결과를 받습니다.
        int result = JaesukService.post(blogDTO);

        // 저장에 실패하면 "error/page" 페이지를 반환합니다.
        if(result <= 0){
            mv.setViewName("error/page");
        }else{
            // 저장에 성공하면 "jaesuk/page" 페이지를 반환합니다.
            mv.setViewName("jaesuk/page");
        }
        System.out.println(result);
        return mv;
    }
    // HTTP 주석 달기 - POST 매핑 개념 설명
    // POST 매핑은 데이터를 서버로 보낼 때 사용됩니다.
=======
    private final JaesukService jaesukService;
    private static final Logger logger = LoggerFactory.getLogger(JaesukController.class);
    private final JaesukRepository jaesukRepository;

    public List<JaesukBlog> getAllPosts() {
        List<JaesukBlog> posts = jaesukRepository.findAll();
        return posts != null ? posts : new ArrayList<>();
    }
    @Autowired
    public JaesukController(JaesukService jaesukService, JaesukRepository jaesukRepository) {
        this.jaesukService = jaesukService;
        this.jaesukRepository = jaesukRepository;
    }

    @GetMapping("")
    public ModelAndView showMainPage() {
        ModelAndView mav = new ModelAndView("jaesuk/page");
        try {
            List<JaesukBlog> posts = jaesukService.getAllPosts();
            mav.addObject("posts", posts);
            logger.info("Showing main page with {} posts", posts.size());
        } catch (Exception e) {
            logger.error("Error fetching posts for main page", e);
            mav.addObject("error", "게시글을 불러오는 중 오류가 발생했습니다.");
        }
        return mav;
    }

    @GetMapping("/post")
    public ModelAndView showNewPostForm() {
        ModelAndView mav = new ModelAndView("jaesuk/post");
        mav.addObject("post", new JaesukBlog());
        return mav;
    }

    @PostMapping("/post")
    public ModelAndView createNewPost(@ModelAttribute JaesukBlog post) {
        ModelAndView mav = new ModelAndView("redirect:/jaesuk");
        try {
            JaesukBlog savedPost = jaesukService.createPost(post);
            logger.info("Created new post: id={}, title={}", savedPost.getBlogNo(), savedPost.getBlogTitle());
            mav.addObject("message", "게시글이 성공적으로 작성되었습니다.");
        } catch (Exception e) {
            logger.error("Error creating new post", e);
            mav.setViewName("redirect:/jaesuk/post");
            mav.addObject("error", "게시글 작성 중 오류가 발생했습니다.");
        }
        return mav;
    }
>>>>>>> Stashed changes

    @GetMapping("/post/{id}")
    public ModelAndView showPost(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("jaesuk/post");
        JaesukBlog post = jaesukService.getPostById(id);
        mav.addObject("post", post);
        logger.info("Showing post with id: {}", id);
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("jaesuk/edit");
        JaesukBlog post = jaesukService.getPostById(id);
        mav.addObject("post", post);
        logger.info("Showing edit form for post with id: {}", id);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updatePost(@PathVariable Integer id, @ModelAttribute JaesukBlog post) {
        ModelAndView mav = new ModelAndView("redirect:/jaesuk");
        try {
            jaesukService.updatePost(id, post);
            logger.info("Updated post with id: {}", id);
            mav.addObject("message", "게시글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            logger.error("Error updating post with id: {}", id, e);
            mav.addObject("error", "게시글 수정 중 오류가 발생했습니다.");
        }
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteConfirmation(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("jaesuk/delete");
        JaesukBlog post = jaesukService.getPostById(id);
        mav.addObject("post", post);
        logger.info("Showing delete confirmation for post with id: {}", id);
        return mav;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePost(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("redirect:/jaesuk");
        try {
            jaesukService.deletePost(id);
            logger.info("Deleted post with id: {}", id);
            mav.addObject("message", "게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            logger.error("Error deleting post with id: {}", id, e);
            mav.addObject("error", "게시글 삭제 중 오류가 발생했습니다.");
        }
        return mav;
    }
}