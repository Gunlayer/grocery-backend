package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithReviewsDTO;
import com.endava.groceryshopservice.entities.dto.ReviewDTO;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ReviewService;
import com.endava.groceryshopservice.services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_TOKEN;
import static com.endava.groceryshopservice.utils.TestConstants.EMPTY_STRING;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_TOKEN;
import static com.endava.groceryshopservice.utils.UserUtils.ADMIN_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest extends BaseController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Test
    void getById_productWithDescDto_correctId() throws Exception {
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);

        ProductWithDescDTO expected = new ProductWithDescDTO(PRODUCT_ONE);

        mockMvc.perform(get(String.format("/products/%d", ID_ONE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(expected)));
    }

    @Test
    void shouldGetProductById() throws Exception {
        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(reviewService.getAllReviewsByProductId(ID_ONE)).thenReturn(REVIEW_LIST_PRODUCT_ONE);

        mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(
                        new ProductWithReviewsDTO(PRODUCT_ONE, REVIEW_LIST_PRODUCT_ONE))));

        verify(productService).getById(ID_ONE);
        verify(reviewService).getAllReviewsByProductId(ID_ONE);
    }

    @Test
    void getById_badRequest_incorrectId() throws Exception {
        when(productService.getById(ID_ONE))
                .thenThrow(new NoProductFoundException(String.format("Could not find a product with specified id[%d]", ID_ONE)));

        mockMvc.perform(get(String.format("/products/%d", ID_ONE)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_productWithDescDTO() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        when(productService.save(PRODUCT_ONE)).thenReturn(PRODUCT_ONE);

        ProductWithDescDTO expected = new ProductWithDescDTO(PRODUCT_ONE);

        mockMvc.perform(post("/products")
                        .header("authorization", ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonString(PRODUCT_ONE))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(expected)));
    }

    @Test
    void getMostPopular_productWithDescDTO_correctSize() throws Exception {
        when(productService.getMostViewed(anyInt())).thenReturn(List.of(PRODUCT_ONE, PRODUCT_TWO));

        List<ProductNoDescDTO> expected = List.of(new ProductNoDescDTO(PRODUCT_ONE), new ProductNoDescDTO(PRODUCT_TWO));

        mockMvc.perform(get("/products/mostpopular"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(expected)));
    }

    @Test
    void getMostPopular_badRequest_incorrectNumber() throws Exception {
        when(productService.getMostViewed(0))
                .thenThrow(new IllegalArgumentException("Number of products must be at least 1"));

        mockMvc.perform(get("/products/mostpopular?number=0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldAddReview() throws Exception {
        prepareAuthorizedRequestForUser(USER_ONE, USER_TOKEN);

        when(productService.getById(ID_ONE)).thenReturn(PRODUCT_ONE);
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);
        when(reviewService.addReview(ID_ONE, USER_EMAIL, REVIEW)).thenReturn(REVIEW);

        mockMvc.perform(post("/products/1/add_review")
                        .header("authorization", USER_TOKEN)
                        .content(createJsonString(REVIEW))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json(createJsonString(new ReviewDTO(REVIEW))));

        verify(reviewService).addReview(ID_ONE, USER_EMAIL, REVIEW);
    }

    @Test
    void getAllProducts_pageProductWithDescDto_PageSizeTwo() throws Exception {
        final long totalProducts = 41L;
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        when(productService.getAll(EMPTY_STRING, PageRequest.of(0, 2)))
                .thenReturn(new PageImpl<>(List.of(PRODUCT_ONE, PRODUCT_TWO)));
        when(productService.getCountAll(EMPTY_STRING)).thenReturn(totalProducts);
        List<ProductWithDescDTO> expected = List.of(new ProductWithDescDTO(PRODUCT_ONE), new ProductWithDescDTO(PRODUCT_TWO));

        mockMvc.perform(get("/products?pageNumber=0&pageSize=2").header("authorization", ADMIN_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(new PageImpl<>(expected, PageRequest.of(0, 2), totalProducts))));
    }

    @Test
    void getAllProducts_Forbidden_Unauthorized() throws Exception {
        mockMvc.perform(get("/products?pageNumber=0&pageSize=2"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void DeleteMapping_ResponseEntityProdWithDesc_correctId() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        when(productService.deleteById(ID_ONE)).thenReturn(PRODUCT_ONE);

        ProductWithDescDTO expected = new ProductWithDescDTO(PRODUCT_ONE);

        mockMvc.perform(delete((String.format("/products/%d", ID_ONE)))
                        .header("authorization", ADMIN_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(expected)));
    }

    @Test
    void DeleteMapping_BadRequest_incorrectId() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        when(productService.deleteById(ID_ONE)).thenThrow(new NoProductFoundException(String.format("Could not find a product with specified id[%d]", ID_ONE)));

        mockMvc.perform(delete((String.format("/products/%d", ID_ONE)))
                        .header("authorization", ADMIN_TOKEN))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void DeleteMapping_Forbidden_Unauthorized() throws Exception {
        mockMvc.perform(delete((String.format("/products/%d", ID_ONE))))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}