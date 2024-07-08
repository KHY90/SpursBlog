package com.ohgiraffers.model.dto;

public class JaesukBlogDTO   {
    private String text;

    public JaesukBlogDTO(String text) {
        this.text = text;
    }

    public JaesukBlogDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JaesukBlogDTO{" +
                "text='" + text + '\'' +
                '}';
    }

}
