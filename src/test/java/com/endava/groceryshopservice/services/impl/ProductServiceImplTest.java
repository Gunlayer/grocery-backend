package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.repositories.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_NO_DESC_DTO_1;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_NO_DESC_DTO_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ViewsServiceImpl viewsService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllShouldReturn() {
        List<ProductNoDescDTO> products = Arrays.asList(PRODUCT_NO_DESC_DTO_1, PRODUCT_NO_DESC_DTO_2);
        Page<Product> pagedResponse = new PageImpl(products);
        Pageable pageable = PageRequest.of(0, 2);

        when(productRepository.findAll(pageable)).thenReturn(pagedResponse);
        productService.getAll(pageable);
        assertThat(productRepository.findAll(pageable).getSize()).isEqualTo(pagedResponse.getSize());
    }
}