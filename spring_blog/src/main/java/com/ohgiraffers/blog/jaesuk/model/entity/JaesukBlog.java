package com.ohgiraffers.blog.jaesuk.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "jaesuk_blog")
public class JaesukBlog {

    @Id
    @Column(name = "blog_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 값을 자동 생성합니다.
    private int blogNo; // int와 Integer의 차이는 int는 기본형이고, Integer는 객체형입니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo; // Interger랑 차이는 뭐지?

    @Column(name = "blog_title", unique = true, nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000)
    private String blogContent;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo;

    @Column(name = "blog_title", nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000)
    private String blogContent = ""; // 기본값 설정

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)  // 날짜와 시간을 저장합니다.
    private Date createDate;

    // 기본 생성자입니다.

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date(); // 기본값으로 현재 날짜 설정

    @Transient // DB에 저장되지 않는 필드
    private String formattedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public JaesukBlog() {
    }

    public JaesukBlog(int blogNo, String blogTitle, String blogContent, Date createDate) {
        this.blogNo = blogNo;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    // blogNo 필드의 값을 반환합니다.

    // Getter and Setter methods
    public int getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(int blogNo) {
        this.blogNo = blogNo;
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

    // 객체의 문자열 표현을 반환합니다.

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return "JaesukBlog{" +
                "blogNo=" + blogNo +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}