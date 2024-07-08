package com.ohgiraffers.blog.hwayeon.controller;

import com.ohgiraffers.blog.hwayeon.model.dto.HwayeonBlogDTO;
import com.ohgiraffers.blog.hwayeon.service.HwayeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hwayeon")
public class HwayeonController {

    private final HwayeonService hwayeonService;

    @Autowired
    public HwayeonController(HwayeonService hwayeonService) {
        this.hwayeonService = hwayeonService;
    }

    // 메인 페이지 불러오기
    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("hwayeon/main"); // ModelAndView 객체 생성 및 View 설정

        List<HwayeonBlogDTO> allPosts = hwayeonService.findAllPosts(); // 모든 게시글 가져오기

        mv.addObject("allPosts", allPosts); // 뷰에 allPosts 객체를 allPosts 이름으로 전달

        return mv; // ModelAndView 객체 반환
    }

    // 리스트 페이지 불러오기
    @GetMapping("/list")
    public ModelAndView listpage() {
        ModelAndView mv = new ModelAndView("hwayeon/listpage");

        List<HwayeonBlogDTO> latestPosts = hwayeonService.findAllPosts();
        mv.addObject("latestPosts", latestPosts);

        return mv;
    }


    // 등록 페이지 불러오기
    @GetMapping("/newpost")
    public ModelAndView showNewPostPage() {
        ModelAndView mv = new ModelAndView("hwayeon/editpage"); // ModelAndView 객체 생성 및 View 설정

        mv.addObject("blog", new HwayeonBlogDTO()); // 뷰에 빈 HwayeonBlogDTO 객체를 blog 이름으로 전달

        return mv; // ModelAndView 객체 반환
    }

    // 수정 페이지 불러오기
    @GetMapping("/editpage")
    public ModelAndView showEditPage(@RequestParam(name = "blogNo", required = false) Integer blogNo) {
        ModelAndView mv = new ModelAndView("hwayeon/modifypage"); // ModelAndView 객체 생성 및 View 설정

        if (blogNo != null) {
            HwayeonBlogDTO blogDTO = hwayeonService.findByBlogNo(blogNo); // blogNo에 해당하는 게시글 조회
            mv.addObject("blog", blogDTO); // 뷰에 조회된 블로그 객체를 blog 이름으로 전달
        } else {
            mv.addObject("blog", new HwayeonBlogDTO()); // blogNo가 없으면 빈 HwayeonBlogDTO 객체를 blog 이름으로 전달
        }

        return mv; // ModelAndView 객체 반환
    }

    // 내용 등록하기
    @PostMapping("/postpage")
    public ModelAndView submitPost(HwayeonBlogDTO blogDTO) {
        hwayeonService.savePost(blogDTO); // 게시글 저장 메소드 호출
        ModelAndView mv = new ModelAndView("hwayeon/postpage"); // ModelAndView 객체 생성 및 View 설정

        mv.addObject("blogTitle", blogDTO.getBlogTitle()); // 뷰에 게시글 제목을 blogTitle 이름으로 전달
        mv.addObject("blogContent", blogDTO.getBlogContent()); // 뷰에 게시글 내용을 blogContent 이름으로 전달
        mv.addObject("blogNo", blogDTO.getBlogNo()); // 뷰에 게시글 번호를 blogNo 이름으로 전달

        return mv; // ModelAndView 객체 반환
    }

    // 내용 수정하기
    @PostMapping("/update")
    public ModelAndView updatePost(HwayeonBlogDTO blogDTO) {
        hwayeonService.updatePost(blogDTO); // 게시글 수정 메소드 호출
        ModelAndView mv = new ModelAndView("hwayeon/postpage"); // ModelAndView 객체 생성 및 View 설정

        mv.addObject("blogTitle", blogDTO.getBlogTitle()); // 뷰에 게시글 제목을 blogTitle 이름으로 전달
        mv.addObject("blogContent", blogDTO.getBlogContent()); // 뷰에 게시글 내용을 blogContent 이름으로 전달
        mv.addObject("blogNo", blogDTO.getBlogNo()); // 뷰에 게시글 번호를 blogNo 이름으로 전달

        return mv; // ModelAndView 객체 반환
    }

    // 삭제 처리하기 (DELETE 메소드)
    @DeleteMapping("/delete/{blogNo}")
    public String deletePost(@PathVariable("blogNo") Integer blogNo) {

        hwayeonService.deletePost(blogNo);  // 게시글 삭제 메소드 호출

        return "redirect:/hwayeon/list";  // 리다이렉트 View 설정
    }


}
