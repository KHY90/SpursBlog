package com.ohgiraffers.blog.jun.service;


// JunService 클래스는 블로그 게시물과 관련된 비즈니스 로직을 처리하는 서비스 클래스

import com.ohgiraffers.blog.jun.model.dto.BlogDTO;
import com.ohgiraffers.blog.jun.model.entity.JunBlog;
import com.ohgiraffers.blog.jun.repository.JunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JunService {

    private final JunRepository junRepository;

    // 생성자 주입을 통해 JunRepository를 인스턴스 변수에 할당
    @Autowired
    public JunService(JunRepository junRepository) {
        this.junRepository = junRepository;
    }

    @org.springframework.transaction.annotation.Transactional
    public int post(BlogDTO blogDTO) {
        JunBlog newBlog = new JunBlog();
        newBlog.setBlogTitle(blogDTO.getBlogTitle());
        newBlog.setBlogContent(blogDTO.getBlogContent());
        newBlog.setCreateDate(new Date());

        JunBlog savedBlog = junRepository.save(newBlog);

        return savedBlog != null ? 1 : 0; // 저장 결과에 따라 1 또는 0 반환
    }

    public List<BlogDTO> getAllBlogs() {
        List<JunBlog> junBlogs = junRepository.findAll();
        List<BlogDTO> blogDTOs = new ArrayList<>();

        for (JunBlog blog : junBlogs) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setId(blog.getId());
            blogDTO.setBlogTitle(blog.getBlogTitle());
            blogDTO.setBlogContent(blog.getBlogContent());
            blogDTO.setCreateDate(blog.getCreateDate());
            blogDTOs.add(blogDTO);
        }

        return blogDTOs;
    }

    public BlogDTO getBlogById(Long id) {
        JunBlog blog = junRepository.findById(id).orElse(null);
        if (blog != null) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setId(blog.getId());
            blogDTO.setBlogTitle(blog.getBlogTitle());
            blogDTO.setBlogContent(blog.getBlogContent());
            blogDTO.setCreateDate(blog.getCreateDate());
            return blogDTO;
        }
        return null;
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteBlogById(Long id) {
        junRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public void saveBlog(BlogDTO blogDTO) {
        JunBlog blog = junRepository.findById(blogDTO.getId()).orElse(null);
        if (blog != null) {
            blog.setBlogTitle(blogDTO.getBlogTitle());
            blog.setBlogContent(blogDTO.getBlogContent());
            junRepository.save(blog);
        }
    }

    @Transactional
    public void updateBlog(BlogDTO blogDTO) {
        JunBlog blog = junRepository.findById(blogDTO.getId()).orElse(null);
        if (blog != null) {
            blog.setBlogTitle(blogDTO.getBlogTitle());
            blog.setBlogContent(blogDTO.getBlogContent());
            junRepository.save(blog);
        }
    }










}

// 게시물과 관련된 비즈니스 로직을 처리
// post 메서드는 블로그 게시물을 등록하며, 블로그 제목이 중복되는지 확인한 후, 중복되지 않으면 새로운 블로그 게시물을 저장
// 트랜잭션 관리를 통해 데이터 일관성을 유지
