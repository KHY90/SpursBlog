package com.ohgiraffers.blog.jaesuk.service;

// 필요한 클래스들을 가져옵니다.

import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import com.ohgiraffers.blog.jaesuk.repository.JaesukRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class JaesukService {

    // JaesukRepository를 사용합니다.

    private static final Logger logger = LoggerFactory.getLogger(JaesukService.class);

    private final JaesukRepository jaesukRepository;

    @Autowired
    public JaesukService(JaesukRepository jaesukRepository) {
        this.jaesukRepository = jaesukRepository;
    }


    // 이 메서드는 트랜잭션으로 처리됩니다.

    @Transactional
    public int post(BlogDTO blogDTO) {
        List<JaesukBlog> jaesukBlogs = jaesukRepository.findAll();
        // 도메인 로직
        for (JaesukBlog blog: jaesukBlogs) {
            if(blog.getBlogTitle().equals(blogDTO.getBlogTitle())){
                return 0;
            }
        }

        JaesukBlog saveBlog = new JaesukBlog();
        saveBlog.setBlogContent(blogDTO.getBlogContent());
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());
        JaesukBlog result  = jaesukRepository.save(saveBlog);

        int resultValue = 0;

        if(result != null){
            resultValue = 1;
        }

        return resultValue;
    }
}
    public List<JaesukBlog> getAllPosts() {
        List<JaesukBlog> posts = jaesukRepository.findAll();
        for (JaesukBlog post : posts) {
            setFormattedDate(post);
        }
        logger.info("Fetched {} posts", posts.size());
        return posts;
    }

    public JaesukBlog getPostById(Integer id) {
        logger.info("Fetching post with id: {}", id);
        return jaesukRepository.findById(id)
                .map(post -> {
                    setFormattedDate(post);
                    return post;
                })
                .orElseThrow(() -> {
                    logger.error("Post not found with id: {}", id);
                    return new RuntimeException("Post not found with id: " + id);
                });
    }

    @Transactional
    public JaesukBlog createPost(JaesukBlog post) {
        if (post.getBlogTitle() == null || post.getBlogTitle().trim().isEmpty()) {
            post.setBlogTitle("");
        }
        if (post.getCreateDate() == null) {
            post.setCreateDate(new Date());
        }
        JaesukBlog savedPost = jaesukRepository.save(post);
        setFormattedDate(savedPost);
        logger.info("Created new post: id={}, title={}", savedPost.getBlogNo(), savedPost.getBlogTitle());
        return savedPost;
    }

    @Transactional
    public JaesukBlog updatePost(Integer id, JaesukBlog updatedPost) {
        JaesukBlog post = getPostById(id);
        post.setBlogTitle(updatedPost.getBlogTitle());
        post.setBlogContent(updatedPost.getBlogContent());
        JaesukBlog savedPost = jaesukRepository.save(post);
        setFormattedDate(savedPost);
        logger.info("Updated post: id={}, title={}", savedPost.getBlogNo(), savedPost.getBlogTitle());
        return savedPost;
    }

    @Transactional
    public void deletePost(Integer id) {
        JaesukBlog post = getPostById(id);
        jaesukRepository.delete(post);
        logger.info("Deleted post: id={}", id);
    }

    private void setFormattedDate(JaesukBlog post) {
        if (post.getCreateDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 HH:mm");
            post.setFormattedDate(sdf.format(post.getCreateDate()));
        }
    }
}
