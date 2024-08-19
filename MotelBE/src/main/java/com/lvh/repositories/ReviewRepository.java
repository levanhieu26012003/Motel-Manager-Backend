/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.Reviews;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface ReviewRepository {    
    Map<String, Object> reviewToJson(Reviews r);
    Reviews jsonToReview(Map<String, String> params);
    List<Reviews> getReviews(Long hostId);
    Reviews getReviewById(Long reviewId);
    void saveOrUpdateReview(Reviews r);
    double averageRating(Long id);
    void deleteReview(Long id);
}
