package com.ohgiraffers.blog.jun.model.entity;


import jakarta.persistence.*;

import java.util.Date;

// JunBlog 클래스는 블로그 게시물 엔티티를 나타냅니다.
@Entity
@Table(name = "jun_blog") // 데이터베이스의 jun_blog 테이블에 매핑된다.
public class JunBlog {

    //기본 키로 사용될 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    @Column(name = "blog_title", unique = true, nullable = false) // 블로그 제목 컬럼, 유니크 제약 조건 및 널 허용 안 함
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000) // 블로그 내용 컬럼, 널 허용 안 하며 최대 길이 5000
    private String blogContent;

    @Column(name = "creation_date") // 생성일 컬럼
    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간을 지정
    private Date createDate;

    // 기본 생성자

    public JunBlog() {
    }

    // 매개변수가 있는 생성자

    public JunBlog(String blogTitle, String blogContent, Date createDate) {

        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    // 객체의 문자열 표현을 반환하는 toString 메서드

    @Override
    public String toString() {
        return "JunBlog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

// 이 클래스는 JPA를 사용하여 데이터베이스의 jaesuk_blog 테이블과 매핑
// 각 필드는 테이블의 컬럼에 매핑되며, blogNo 필드는 기본 키로 설정