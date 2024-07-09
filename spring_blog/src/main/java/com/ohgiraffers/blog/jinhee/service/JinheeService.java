package com.ohgiraffers.blog.jinhee.service;

import com.ohgiraffers.blog.jinhee.model.dto.BlogDTO;
import com.ohgiraffers.blog.jinhee.model.entity.JinheeBlog;
import com.ohgiraffers.blog.jinhee.repository.JinheeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service // Spring의 서비스 컴포넌트임을 나타내는 애노테이션
public class JinheeService {

    private final JinheeRepository jinheeRepository;

    @Autowired // Spring이 JinheeRepository를 주입하도록 하는 애노테이션
    public JinheeService(JinheeRepository jinheeRepository) {
        this.jinheeRepository = jinheeRepository;
    }

    @Transactional // 트랜잭션 처리를 위한 애노테이션
    public int post(BlogDTO blogDTO) {
        JinheeBlog newBlog = new JinheeBlog();
        newBlog.setBlogTitle(blogDTO.getBlogTitle());
        newBlog.setBlogContent(blogDTO.getBlogContent());
        newBlog.setCreateDate(new Date());

        JinheeBlog savedBlog = jinheeRepository.save(newBlog);

        return savedBlog != null ? 1 : 0; // 저장 결과에 따라 1 또는 0 반환
    }

    public List<BlogDTO> getAllBlogs() {
        List<JinheeBlog> jinheeBlogs = jinheeRepository.findAll();
        List<BlogDTO> blogDTOs = new ArrayList<>();

        for (JinheeBlog blog : jinheeBlogs) {
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
        JinheeBlog blog = jinheeRepository.findById(id).orElse(null);
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

    @Transactional // 트랜잭션 처리를 위한 애노테이션
    public void deleteBlogById(Long id) {
        jinheeRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 처리를 위한 애노테이션
    public void saveBlog(BlogDTO blogDTO) {
        JinheeBlog blog = jinheeRepository.findById(blogDTO.getId()).orElse(null);
        if (blog != null) {
            blog.setBlogTitle(blogDTO.getBlogTitle());
            blog.setBlogContent(blogDTO.getBlogContent());
            jinheeRepository.save(blog);
        }
    }

    @Transactional // 트랜잭션 처리를 위한 애노테이션
    public void updateBlog(BlogDTO blogDTO) {
        JinheeBlog blog = jinheeRepository.findById(blogDTO.getId()).orElse(null);
        if (blog != null) {
            blog.setBlogTitle(blogDTO.getBlogTitle());
            blog.setBlogContent(blogDTO.getBlogContent());
            jinheeRepository.save(blog);
        }
    }
}
