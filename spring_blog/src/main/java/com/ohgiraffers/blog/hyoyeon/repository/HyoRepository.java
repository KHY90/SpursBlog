package com.ohgiraffers.blog.hyoyeon.repository;

import com.ohgiraffers.blog.hyoyeon.model.entity.HyoBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HyoRepository extends JpaRepository<HyoBlog, Integer> {

}
