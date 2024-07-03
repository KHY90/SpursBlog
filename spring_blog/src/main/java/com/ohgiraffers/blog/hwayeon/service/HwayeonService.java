package com.ohgiraffers.blog.hwayeon.service;

import com.ohgiraffers.blog.hwayeon.model.dto.hwayeonBlogDTO;
import com.ohgiraffers.blog.hwayeon.model.entity.HwayeonBlog;
import com.ohgiraffers.blog.hwayeon.repository.HwayeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HwayeonService {

    private final HwayeonRepository hwayeonRepository; // HwayeonRepository 의존성 주입을 위한 필드

    @Autowired
    public HwayeonService(HwayeonRepository hwayeonRepository) {
        this.hwayeonRepository = hwayeonRepository; // 생성자를 통한 의존성 주입
    }

    // 블로그 글 등록 서비스 메서드
    @Transactional
    public int post(hwayeonBlogDTO hyblogDTO) {
        List<HwayeonBlog> hwayeonBlogs = hwayeonRepository.findAll(); // 모든 블로그 가져오기

        // 중복 체크 로직
        for (HwayeonBlog blog : hwayeonBlogs) {
            if (blog.getBlogTitle().equals(hyblogDTO.getBlogTitle())) { // 제목이 이미 존재하면
                return 0; // 실패 반환
            }
        }

        // 새로운 블로그 엔티티 생성 및 값 설정
        HwayeonBlog saveBlog = new HwayeonBlog();
        saveBlog.setBlogContent(hyblogDTO.getBlogContent());
        saveBlog.setBlogTitle(hyblogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());

        // 저장된 블로그 엔티티 반환
        HwayeonBlog result = hwayeonRepository.save(saveBlog);

        return result != null ? 1 : 0; // 성공적으로 저장되었는지 여부에 따라 반환
    }

}
