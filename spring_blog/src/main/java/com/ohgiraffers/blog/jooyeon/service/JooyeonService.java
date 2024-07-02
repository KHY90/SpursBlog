package com.ohgiraffers.blog.jooyeon.service;

import com.ohgiraffers.blog.jooyeon.dto.BlogDTO;
import com.ohgiraffers.blog.jooyeon.entity.JooyeonBlog;

import com.ohgiraffers.blog.jooyeon.repository.JooyeonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JooyeonService {

    private final JooyeonRepository jooyeonRepository;


    @Autowired
    public JooyeonService(JooyeonRepository jooyeonRepository) {
        this.jooyeonRepository = jooyeonRepository;
    }

    @Transactional
    public int post(BlogDTO blogDTO) {
        List<JooyeonBlog> jooyeonBlogs = jooyeonRepository.findAll();
        // 도메인 로직
        for (JooyeonBlog blog: jooyeonBlogs) {
            if(blog.getBlogTitle().equals(blogDTO.getBlogTitle())){
                return 0;
            }
        }


        JooyeonBlog saveBlog = new JooyeonBlog();
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
        saveBlog.setBlogContent(blogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());

        JooyeonBlog result = jooyeonRepository.save(saveBlog);

        int resultValue = 0;
        if(result != null){
            resultValue = 1;

        }
        return resultValue;

    }

}
