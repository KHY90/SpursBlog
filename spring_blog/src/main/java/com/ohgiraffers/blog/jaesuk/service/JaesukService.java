package com.ohgiraffers.blog.jaesuk.service;

<<<<<<< Updated upstream
// 필요한 클래스들을 가져옵니다.
import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
=======
>>>>>>> Stashed changes
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

// 이 클래스는 서비스 클래스임을 나타냅니다.
@Service
public class JaesukService {

<<<<<<< Updated upstream
    // JaesukRepository를 사용합니다.
=======
    private static final Logger logger = LoggerFactory.getLogger(JaesukService.class);
>>>>>>> Stashed changes
    private final JaesukRepository jaesukRepository;

    // 생성자를 통해 JaesukRepository를 주입받습니다.
    @Autowired
    public JaesukService(JaesukRepository jaesukRepository) {
        this.jaesukRepository = jaesukRepository;
    }

<<<<<<< Updated upstream
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
=======
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
>>>>>>> Stashed changes
