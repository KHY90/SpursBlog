package com.ohgiraffers.blog.jinhee.service;

import com.ohgiraffers.blog.jinhee.model.dto.BlogDTO;
import com.ohgiraffers.blog.jinhee.model.entity.JinheeBlog;
import com.ohgiraffers.blog.jinhee.repository.JinheeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class JinheeService {

    private final JinheeRepository jinheeRepository;

    @Autowired
    public JinheeService(JinheeRepository jinheeRepository) {
        this.jinheeRepository = jinheeRepository;
    }

    @Transactional
    public int post(BlogDTO blogDTO) {
        List<JinheeBlog> jinheeBlogs = jinheeRepository.findAll();
        // 도메인 로직
        for (JinheeBlog blog: jinheeBlogs) {
            if(blog.getBlogTitle().equals(blogDTO.getBlogTitle())){
                return 0;
            }
        }

        JinheeBlog saveBlog = new JinheeBlog();
        saveBlog.setBlogContent(blogDTO.getBlogContent());
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());
        JinheeBlog result  = jinheeRepository.save(saveBlog);

        int resultValue = 0;

        if(result != null){
            resultValue = 1;
        }

        return resultValue;
    }
}
