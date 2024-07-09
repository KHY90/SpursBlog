package com.ohgiraffers.blog.jinhee.repository;

import com.ohgiraffers.blog.jinhee.model.entity.JinheeBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Spring의 저장소(repository) 컴포넌트임을 나타내는 애노테이션
public interface JinheeRepository extends JpaRepository<JinheeBlog, Long> {
    // JpaRepository 인터페이스를 상속받아 기본적인 CRUD 메서드를 제공
    // JinheeBlog 엔티티와 기본 키 타입 Long을 지정
}
