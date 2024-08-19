/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.lvh.pojo.Reviews;
import com.lvh.repositories.ReviewRepository;
import com.lvh.services.ReviewService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author levan
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Map<String, Object> reviewToJson(Reviews r) {
        return this.reviewToJson(r);
    }

    @Override
    public Reviews jsonToReview(Map<String, String> params) {
        return reviewRepository.jsonToReview(params);
    }

    @Override
    public List<Reviews> getReviews(Long hostId) {
        return this.reviewRepository.getReviews(hostId);
    }

    @Override
    public Reviews getReviewById(Long reviewId) {
        return this.reviewRepository.getReviewById(reviewId);
    }

    @Override
    public void saveOrUpdateReview(Reviews r) {
        this.reviewRepository.saveOrUpdateReview(r);
    }

    @Override
    public void deleteReview(Long id) {
        this.reviewRepository.deleteReview(id);
    }

    @Override
    public double averageRating(Long id) {
        return this.reviewRepository.averageRating(id);
    }

}
