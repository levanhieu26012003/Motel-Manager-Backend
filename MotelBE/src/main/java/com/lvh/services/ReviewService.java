/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services;

import com.lvh.pojo.Reviews;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface ReviewService {
    Map<String, Object> reviewToJson(Reviews r);
    Reviews jsonToReview(Map<String, String> params);
    List<Reviews> getReviews(Long hostId);
    Reviews getReviewById(Long reviewId);
    void saveOrUpdateReview(Reviews r);
    void deleteReview(Long id);
    double averageRating(Long id);
}
