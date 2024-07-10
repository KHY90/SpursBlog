package com.ohgiraffers.blog.hyoyeon.model.dto;

import java.time.LocalDateTime;

public class HyoDTO {


    private String blogAuthor;
    private String blogTitle;
    private String blogContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public HyoDTO(){

    }

    public HyoDTO(String blogAuthor, String blogTitle, String blogContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.blogAuthor = blogAuthor;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(String blogAuthor) {
        this.blogAuthor = blogAuthor;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "HyoDTO{" +
                "blogAuthor='" + blogAuthor + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

}
