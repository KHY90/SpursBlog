package com.ohgiraffers.controller;

import com.ohgiraffers.model.dto.JaesukBlogDTO;
import com.ohgiraffers.service.JaesukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
// 기본페이지 경로 만들고 jaesuk 페이지로 연결
class JaesukController {

    @Autowired
    private JaesukService jaesukService;

    // 페이지 넘겨서 인풋창 만들고 데이터 입력할수있게 만들기
    // 게시글 등록할수있는 페이지
    // sumit?넣어서 데이터 보내기

    @GetMapping("/")
    private String idex() {
        return "/jaesuk";
    }


    // my sql 연결하기
    @GetMapping("/jaesuk")
    public String indexjaesuk(){
        return "/jaesuk";
    }

    @PostMapping("/j_page_save")
    public void saveText(JaesukBlogDTO dto) {
        jaesukService.save(dto);
    }

}