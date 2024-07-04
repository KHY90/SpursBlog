package com.ohgiraffers.blog.jinhee.repository;

import com.ohgiraffers.blog.jinhee.model.entity.JinheeBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JinheeRepository extends JpaRepository<JinheeBlog, Long> {
}
