package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.entities.dto.ProductNoDescDTO;
import com.endava.groceryshopservice.entities.types.SizeTypes;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.ViewsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.TestConstants.DESCRIPTION;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.NAME;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.SIZES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ViewsService viewsService;

    @InjectMocks
    private ProductServiceImpl productService;

    public Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(ID_THREE)
                .image(IMAGE)
                .name(NAME)
                .price(PRICE)
                .rating(RATING)
                .description(DESCRIPTION)
                .sizes(SIZES)
                .sizeType(SizeTypes.PACKS)
                .build();
    }

    @Test
    void getAllShouldReturnPageWithCorrectNumberOfProducts() {
        List<ProductNoDescDTO> products = Arrays.asList(new ProductNoDescDTO(PRODUCT_ONE), new ProductNoDescDTO(PRODUCT_TWO));
        Page<Product> pagedResponse = new PageImpl(products);
        Pageable pageable = PageRequest.of(0, 2);

        when(productRepository.findAll(pageable)).thenReturn(pagedResponse);
        productService.getAll(pageable);
        assertThat(productRepository.findAll(pageable).getSize()).isEqualTo(pagedResponse.getSize());
    }

    @Test
    void getByIdShouldReturnProduct() {
        product.setViews(new Views(product, 0));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        doAnswer((Answer<Void>) invocation -> {
            product.getViews().setNumber(product.getViews().getNumber() + 1);
            return null;
        }).when(viewsService).increaseViewNumber(product.getViews());

        Product actualProduct = productService.getById(product.getId());

        assertAll(
                () -> assertThat(actualProduct).isNotNull(),
                () -> assertThat(actualProduct.getViews()).isNotNull(),
                () -> assertThat(actualProduct.getViews().getNumber()).isEqualTo(product.getViews().getNumber())
        );
    }

    @Test
    void getByIdShouldThrowNoProductFoundException() {
        product.setViews(new Views(product, 0));
        when(productRepository.findById(product.getId())).thenThrow(new NoProductFoundException(String.format("Could not find a product with specified id[%d]", product.getId())));

        assertThatThrownBy(() -> productService.getById(product.getId())).isInstanceOf(NoProductFoundException.class).hasMessage("Could not find a product with specified id[%d]", product.getId());
    }

    @Test
    void getMostViewedShouldReturnProductsList() {
        product.setViews(new Views(product, 0));

        when(viewsService.getMostViewed(1)).thenReturn(List.of(product.getViews()));

        List<Product> products = productService.getMostViewed(1);

        assertThat(products).isEqualTo(List.of(product));
    }

    @Test
    void saveProductShouldReturnProductWithZeroViews() {

        when(productRepository.save(any())).thenAnswer(requestProduct -> requestProduct.getArgument(0));

        Product actualProduct = productService.save(product);

        assertAll(
                () -> assertThat(actualProduct.getViews()).isNotNull(),
                () -> assertThat(actualProduct.getViews().getNumber()).isEqualTo(0L)
        );
    }

    @Test
    void shouldCalculateRating(){
        productRepository.setRatingForProduct(ID_ONE);

        verify(productRepository, times(1)).setRatingForProduct(ID_ONE);
    }
}