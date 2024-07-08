package com.ohgiraffers.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jasuk_blog")
public class JaesukBlog {

    @Id
    @Column(name = "blog_kjs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo;

    @Column(name = "text")
    private String text;

    public JaesukBlog() {
    }

    // Getter와 Setter 메서드 추가
    public int getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(int blogNo) {
        this.blogNo = blogNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
