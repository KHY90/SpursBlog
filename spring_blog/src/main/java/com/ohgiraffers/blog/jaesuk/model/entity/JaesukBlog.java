package com.ohgiraffers.blog.jaesuk.model.entity;

// JPA의 엔티티 클래스를 위한 어노테이션을 가져옵니다.
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

// 이 클래스는 데이터베이스 테이블에 매핑됩니다.
@Entity
@Table(name = "jaesuk_blog")
public class JaesukBlog {

    // 이 필드는 테이블의 기본 키입니다.
    @Id
    @Column(name = "blog_no")
<<<<<<< Updated upstream
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 값을 자동 생성합니다.
    private int blogNo; // int와 Integer의 차이는 int는 기본형이고, Integer는 객체형입니다.

    // 이 필드는 블로그 제목을 나타냅니다.
    @Column(name = "blog_title", unique = true, nullable = false)  // 제목은 고유하고 비어 있을 수 없습니다.
    private String blogTitle;

    // 이 필드는 블로그 내용을 나타냅니다.
    @Column(name = "blog_content", nullable = false, length = 5000)  // 내용은 비어 있을 수 없고 최대 5000자입니다.
    private String blogContent;
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo;

    @Column(name = "blog_title", nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000)
    private String blogContent = ""; // 기본값 설정
>>>>>>> Stashed changes

    // 이 필드는 블로그 작성 날짜를 나타냅니다.
    @Column(name = "creation_date")
<<<<<<< Updated upstream
    @Temporal(TemporalType.TIMESTAMP)  // 날짜와 시간을 저장합니다.
    private Date createDate;

    // 기본 생성자입니다.
=======
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date(); // 기본값으로 현재 날짜 설정

    @Transient // DB에 저장되지 않는 필드
    private String formattedDate;

>>>>>>> Stashed changes
    public JaesukBlog() {
    }

    // 모든 필드를 초기화하는 생성자입니다.
    public JaesukBlog(int blogNo, String blogTitle, String blogContent, Date createDate) {
        this.blogNo = blogNo;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

<<<<<<< Updated upstream
    // blogNo 필드의 값을 반환합니다.
=======
    // Getter and Setter methods

>>>>>>> Stashed changes
    public int getBlogNo() {
        return blogNo;
    }

    // blogNo 필드의 값을 설정합니다.
    public void setBlogNo(int blogNo) {
        this.blogNo = blogNo;
    }

    // blogTitle 필드의 값을 반환합니다.
    public String getBlogTitle() {
        return blogTitle;
    }

    // blogTitle 필드의 값을 설정합니다.
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    // blogContent 필드의 값을 반환합니다.
    public String getBlogContent() {
        return blogContent;
    }

    // blogContent 필드의 값을 설정합니다.
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    // createDate 필드의 값을 반환합니다.
    public Date getCreateDate() {
        return createDate;
    }

    // createDate 필드의 값을 설정합니다.
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

<<<<<<< Updated upstream
    // 객체의 문자열 표현을 반환합니다.
=======
    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

>>>>>>> Stashed changes
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