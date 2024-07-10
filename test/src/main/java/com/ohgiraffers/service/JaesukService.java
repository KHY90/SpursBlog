package com.ohgiraffers.service;

import com.ohgiraffers.model.dto.JaesukBlogDTO;
import com.ohgiraffers.repository.JaesukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JaesukService implements J_service {



    @Autowired
    private JaesukRepository jaesukRepository;


    @Override
    public void save(JaesukBlogDTO Dto) {
        jaesukRepository.save(Dto);

    }


}

