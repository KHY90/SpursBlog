package com.ohgiraffers.blog.jaesuk.service;

// 필요한 클래스들을 가져옵니다.
import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import com.ohgiraffers.blog.jaesuk.repository.JaesukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

// 이 클래스는 서비스 클래스임을 나타냅니다.
@Service
public class JaesukService {

    // JaesukRepository를 사용합니다.
    private final JaesukRepository jaesukRepository;

    // 생성자를 통해 JaesukRepository를 주입받습니다.
    @Autowired
    public JaesukService(JaesukRepository jaesukRepository) {
        this.jaesukRepository = jaesukRepository;
    }

    // 이 메서드는 트랜잭션으로 처리됩니다.
    @Transactional
    public int post(BlogDTO blogDTO) {
        // 모든 블로그 게시글을 가져옵니다.
        List<JaesukBlog> jaesukBlogs = jaesukRepository.findAll();
        // 도메인 로직: 블로그 제목이 이미 존재하는지 확인합니다.
        for (JaesukBlog blog: jaesukBlogs) {
            if(blog.getBlogTitle().equals(blogDTO.getBlogTitle())){
                return 0;  // 이미 같은 제목의 블로그가 있으면 0을 반환합니다.
            }
        }

        // 새로운 블로그 객체를 생성합니다.
        JaesukBlog saveBlog = new JaesukBlog();
        saveBlog.setBlogContent(blogDTO.getBlogContent());  // 블로그 내용을 설정합니다.
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());  // 블로그 제목을 설정합니다.
        saveBlog.setCreateDate(new Date());  // 현재 날짜를 설정합니다.
        JaesukBlog result  = jaesukRepository.save(saveBlog);  // 블로그를 저장합니다.

        // 결과 값을 초기화합니다.

        return result != null ? 1 : 0; // 성공적으로 저장되었는지 여부에 따라 반환
    }



}
