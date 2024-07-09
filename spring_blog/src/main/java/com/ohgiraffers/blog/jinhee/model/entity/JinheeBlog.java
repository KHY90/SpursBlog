package com.ohgiraffers.blog.jinhee.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity // JPA 엔티티 클래스임을 나타내는 애노테이션
@Table(name = "jinhee_blog") // 엔티티와 매핑되는 테이블 정보를 설정
public class JinheeBlog {

    @Id // 기본 키(primary key) 필드를 나타내는 애노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값 생성을 데이터베이스에 위임
    @Column(name = "blog_id") // 해당 필드와 매핑되는 테이블 컬럼 이름을 설정
    private Long id; // 블로그 ID를 저장하는 필드

    @Column(name = "blog_title", nullable = false) // 컬럼 매핑 설정
    private String blogTitle; // 블로그 제목을 저장하는 필드

    @Column(name = "blog_content", nullable = false, length = 5000) // 컬럼 매핑 설정
    private String blogContent; // 블로그 내용을 저장하는 필드

    @Column(name = "creation_date", nullable = false) // 컬럼 매핑 설정
    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입을 타임스탬프로 지정
    private Date createDate; // 블로그 생성 날짜를 저장하는 필드

    // 기본 생성자
    public JinheeBlog() {
    }

    // 모든 필드를 초기화하는 생성자
    public JinheeBlog(String blogTitle, String blogContent, Date createDate) {
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    // 블로그 ID를 반환하는 getter 메서드
    public Long getId() {
        return id;
    }

    // 블로그 ID를 설정하는 setter 메서드
    public void setId(Long id) {
        this.id = id;
    }

    // 블로그 제목을 반환하는 getter 메서드
    public String getBlogTitle() {
        return blogTitle;
    }

    // 블로그 제목을 설정하는 setter 메서드
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    // 블로그 내용을 반환하는 getter 메서드
    public String getBlogContent() {
        return blogContent;
    }

    // 블로그 내용을 설정하는 setter 메서드
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    // 블로그 생성 날짜를 반환하는 getter 메서드
    public Date getCreateDate() {
        return createDate;
    }

    // 블로그 생성 날짜를 설정하는 setter 메서드
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    // JinheeBlog 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        return "JinheeBlog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
