package com.myproject.BackendApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.repository.PostsRepository;

@Service
public class PostsServiceImpl {
    
    @Autowired
    private PostsRepository postsRepo;
}
