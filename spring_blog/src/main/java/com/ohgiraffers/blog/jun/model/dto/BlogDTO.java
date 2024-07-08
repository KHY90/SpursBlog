package com.ohgiraffers.blog.jun.model.dto;

import java.util.Date;

//BlogDTO 클래스는 블로그 게시물의 데이터를 담는 데이터 전송 객체
public class BlogDTO {


    private long id;
    private String blogTitle;
    private String blogContent;
    private Date createDate;

    public BlogDTO() {
    }

    public BlogDTO(long id, String blogTitle, String blogContent, Date createDate) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

// 블로그 게시물의 데이터를 저장하는 'BlogDTO' 클래스.
// 'BlogDTO'는 데이터 전송 객체로, 블로그 제목과 내용을 저장하기 위한 변수를 갖고있음
// 기본 생성자와 매개변수가 있는 생성자를 제공하여 객체를 생성할 수 있음
//각 변수에 접근하고 수정할 수 있는 getter와 setter 메서드가 포함되어 있습니다. toString 메서드는 객체의 상태를 문자열로 표현하는 데 사용됩니다.
