package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.Review;
import com.endava.groceryshopservice.entities.dto.ReviewDTO;
import com.endava.groceryshopservice.entities.dto.ReviewForProductDTO;
import com.endava.groceryshopservice.services.ReviewService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest extends BaseController {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    @Test
    void shouldGetAllReviews() throws Exception {
        List<Review> test = new ArrayList(REVIEW_LIST_PRODUCT_ONE);
        test.addAll(REVIEW_LIST_PRODUCT_TWO);
        List<ReviewDTO> reviewDTOList = test.stream()
                .map(ReviewDTO::new)
                .collect(Collectors.toList());

        when(reviewService.getAllReviews()).thenReturn(test);
        mockMvc.perform(get("/reviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(reviewDTOList)));

        verify(reviewService).getAllReviews();
    }

    @Test
    void shouldGetAllReviewsByProductId() throws Exception {
        List<ReviewForProductDTO> reviewDTOList = REVIEW_LIST_PRODUCT_ONE.stream()
                .map(ReviewForProductDTO::new)
                .collect(Collectors.toList());

        when(reviewService.getAllReviewsByProductId(ID_ONE)).thenReturn(REVIEW_LIST_PRODUCT_ONE);
        mockMvc.perform(get("/reviews/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(reviewDTOList)));

        verify(reviewService).getAllReviewsByProductId(ID_ONE);
    }
}