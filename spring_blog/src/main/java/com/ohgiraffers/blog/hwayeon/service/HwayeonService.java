package com.ohgiraffers.blog.hwayeon.service;

import com.ohgiraffers.blog.hwayeon.model.dto.HwayeonBlogDTO;
import com.ohgiraffers.blog.hwayeon.model.entity.HwayeonBlog;
import com.ohgiraffers.blog.hwayeon.repository.HwayeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HwayeonService {

    private final HwayeonRepository hwayeonRepository;

    @Autowired
    public HwayeonService(HwayeonRepository hwayeonRepository) {
        this.hwayeonRepository = hwayeonRepository;
    }

//    public List<HwayeonBlogDTO> findAllPosts() {
//        return hwayeonBlogRepository.findAll();
//    }

    // 모든 게시글 목록 조회
    public List<HwayeonBlogDTO> findAllPosts() {
        // 데이터베이스에서 모든 게시글을 조회하여 HwayeonBlog 객체의 리스트로 받아옴
        List<HwayeonBlog> allBlogs = hwayeonRepository.findAll();

        // HwayeonBlog 객체 리스트를 HwayeonBlogDTO 객체 리스트로 변환하여 반환
        return allBlogs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 게시글 저장
    public void savePost(HwayeonBlogDTO blogDTO) {
        // HwayeonBlogDTO 객체에서 데이터를 추출하여 HwayeonBlog 객체에 설정
        HwayeonBlog blog = new HwayeonBlog();
        blog.setBlogTitle(blogDTO.getBlogTitle());
        blog.setBlogContent(blogDTO.getBlogContent());

        // 설정된 HwayeonBlog 객체를 저장
        hwayeonRepository.save(blog);
    }

    // 게시글 번호로 게시글 조회
    public HwayeonBlogDTO findByBlogNo(Integer blogNo) {
        // 게시글 번호로 데이터베이스에서 게시글 조회, 결과가 없으면 예외 발생
        HwayeonBlog blog = hwayeonRepository.findById(blogNo)
                .orElseThrow(() -> new RuntimeException("Blog를 찾을 수 없습니다."));

        // 조회된 HwayeonBlog 객체를 HwayeonBlogDTO 객체로 변환하여 반환
        return convertToDTO(blog);
    }

    // 게시글 수정
    public void updatePost(HwayeonBlogDTO blogDTO) {
        Integer blogNo = blogDTO.getId(); // postDto에서 ID 추출
        if (blogNo == null) {
            throw new IllegalArgumentException("Post ID must not be null");
        }

        // 게시글 번호로 데이터베이스에서 게시글 조회, 결과가 없으면 예외 발생
        HwayeonBlog blog = hwayeonRepository.findById(blogNo)
                .orElseThrow(() -> new RuntimeException("Blog를 찾을 수 없습니다."));

        // HwayeonBlogDTO 객체에서 데이터를 추출하여 HwayeonBlog 객체에 설정
        blog.setBlogTitle(blogDTO.getBlogTitle());
        blog.setBlogContent(blogDTO.getBlogContent());

        // 수정된 HwayeonBlog 객체를 저장
        hwayeonRepository.save(blog);
    }

    // HwayeonBlog 객체를 HwayeonBlogDTO 객체로 변환
    private HwayeonBlogDTO convertToDTO(HwayeonBlog blog) {
        return new HwayeonBlogDTO(
                blog.getBlogNo(),
                blog.getBlogTitle(),
                blog.getBlogContent(),
                blog.getCreateDate(),
                blog.getImgUrl(),
                blog.getCategory()
        );

    }

    // 게시글 삭제
    public void deletePost(Integer blogNo) {
        // 게시글 번호로 데이터베이스에서 게시글 삭제
        hwayeonRepository.deleteById(blogNo);
    }

}
