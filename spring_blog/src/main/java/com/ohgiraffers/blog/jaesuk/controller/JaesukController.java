package com.ohgiraffers.blog.jaesuk.controller;

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
@RequestMapping("/jaesuk")
class JaesukController {

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

}
