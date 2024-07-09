package com.ohgiraffers.blog.jinhee.model.dto;

import java.util.Date;

// BlogDTO 클래스는 블로그 게시글의 데이터 전송 객체(Data Transfer Object)입니다.
public class BlogDTO {

    // 블로그 ID를 저장하는 필드
    private long id;

    // 블로그 제목을 저장하는 필드
    private String blogTitle;

    // 블로그 내용을 저장하는 필드
    private String blogContent;

    // 블로그 생성 날짜를 저장하는 필드
    private Date createDate;

    // 기본 생성자
    public BlogDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public BlogDTO(long id, String blogTitle, String blogContent, Date createDate) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    // 블로그 ID를 반환하는 getter 메서드
    public long getId() {
        return id;
    }

    // 블로그 ID를 설정하는 setter 메서드
    public void setId(long id) {
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

    // BlogDTO 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        return "BlogDTO{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
