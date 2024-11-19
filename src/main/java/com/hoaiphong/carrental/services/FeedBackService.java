package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.entities.FeedBack;

public interface FeedBackService {

    void save(FeedBack feedBack);

    FeedBack findById(UUID id);

    List<FeedBack> findAll();

    public Page<FeedBack> findAllFeedback(Pageable pageable);
    List<FeedBack> findByFeedBackID(UUID feedBackId);
    
}