package com.ohgiraffers.blog.jun.model.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "jun_blog")
public class JunBlog {

    @Id
    @Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogid;

    @Column(name = "blog_title", unique = true, nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000)
    private String blogContent;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public JunBlog() {
    }

    public JunBlog(Long blogid, String blogTitle, String blogContent, Date createDate) {
        this.blogid = blogid;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    public Long getBlogid() {
        return blogid;
    }

    public void setBlogid(Long blogid) {
        this.blogid = blogid;
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

    @Override
    public String toString() {
        return "JunBlog{" +
                "blogid=" + blogid +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

// 이 클래스는 JPA를 사용하여 데이터베이스의 jaesuk_blog 테이블과 매핑
// 각 필드는 테이블의 컬럼에 매핑되며, blogNo 필드는 기본 키로 설정