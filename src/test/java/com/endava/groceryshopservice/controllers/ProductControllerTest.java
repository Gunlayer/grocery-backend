package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.dto.ProductWithDescDTO;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.services.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest extends BaseController {

    @MockBean
    ProductService productService;

    @Test
    void getAll_pageNoProdDto_correctData() throws Exception {
        when(productService.getAll(PageRequest.of(0, 40)))
                .thenReturn(new PageImpl<>(List.of(PRODUCT_ONE, PRODUCT_TWO)));
        List<ProductNoDescDTO> expected = List.of(new ProductNoDescDTO(PRODUCT_ONE), new ProductNoDescDTO(PRODUCT_TWO));

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(new PageImpl(expected))));
    }

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
    void getById_badRequest_incorrectId() throws Exception {
        when(productService.getById(ID_ONE))
                .thenThrow(new NoProductFoundException(String.format("Could not find a product with specified id[%d]", ID_ONE)));

        mockMvc.perform(get(String.format("/products/%d", ID_ONE)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_productWithDescDTO() throws Exception {
        when(productService.save(PRODUCT_ONE)).thenReturn(PRODUCT_ONE);

        ProductWithDescDTO expected = new ProductWithDescDTO(PRODUCT_ONE);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonString(PRODUCT_ONE))).andDo(print())
                .andExpect(status().isOk())
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
}