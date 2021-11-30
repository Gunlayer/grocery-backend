package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.ProductUtils.PRODUCT_TWO;
import static com.endava.groceryshopservice.utils.ReviewUtils.REVIEW_LIST_PRODUCT_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.DESCRIPTION;
import static com.endava.groceryshopservice.utils.TestConstants.EMPTY_STRING;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.ID_THREE;
import static com.endava.groceryshopservice.utils.TestConstants.IMAGE;
import static com.endava.groceryshopservice.utils.TestConstants.PLUMS;
import static com.endava.groceryshopservice.utils.TestConstants.PRICE;
import static com.endava.groceryshopservice.utils.TestConstants.RATING;
import static com.endava.groceryshopservice.utils.TestConstants.SIZES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    private Product product;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ViewsService viewsService;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .image(IMAGE)
                .name(PLUMS)
                .price(PRICE)
                .rating(RATING)
                .description(DESCRIPTION)
                .sizes(SIZES)
                .sizeType(SizeTypes.PACKS)
                .build();
    }

    @Test
    void getByIdShouldReturnProduct() {
        product.setViews(new Views(product, 0));
        when(productRepository.findById(ID_THREE)).thenReturn(Optional.of(product));
        doAnswer((Answer<Void>) invocation -> {
            product.getViews().setNumber(product.getViews().getNumber() + 1);
            return null;
        }).when(viewsService).increaseViewNumber(product.getViews());

        Product actualProduct = productService.getById(ID_THREE);

        assertAll(
                () -> assertThat(actualProduct).isNotNull(),
                () -> assertThat(actualProduct.getViews()).isNotNull(),
                () -> assertThat(actualProduct.getViews().getNumber()).isEqualTo(product.getViews().getNumber())
        );
    }

    @Test
    void getByIdShouldThrowNoProductFoundException() {
        when(productRepository.findById(ID_THREE)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> productService.getById(ID_THREE)
        ).isInstanceOf(NoProductFoundException.class)
                .hasMessage("Could not find a product with specified id[%d]", ID_THREE);
    }

    @Test
    void getMostViewedShouldReturnProductsList() {
        final int amount = 1;
        product.setViews(new Views(product, 56));

        when(viewsService.getMostViewed(amount)).thenReturn(List.of(product.getViews()));

        List<Product> products = productService.getMostViewed(amount);

        assertAll(
                () -> assertThat(products).isNotNull(),
                () -> assertThat(products).isNotEmpty(),
                () -> assertThat(products).hasSize(amount),
                () -> assertThat(products).containsOnly(product)
        );
    }

    @Test
    void save_ProductWithIdAndZeroNumberOfViews_newProduct() {
        Product expectedProduct = Product.builder().id(ID_THREE).image(IMAGE).name(PLUMS).price(PRICE)
                .rating(RATING).description(DESCRIPTION).sizes(SIZES).sizeType(SizeTypes.PACKS).build();
        expectedProduct.setViews(new Views(expectedProduct, 0));
        when(productRepository.save(product)).thenAnswer(invocation -> {
            product.setId(ID_THREE);
            return product;
        });

        productService.save(product);

        assertAll(
                () -> assertThat(product).isNotNull(),
                () -> assertThat(product).isEqualTo(expectedProduct),
                () -> assertThat(product.getViews()).isNotNull(),
                () -> assertThat(product.getViews()).isEqualTo(expectedProduct.getViews())
        );
    }

    @Test
    void save_ProductWithViewsAndReview_existedProduct() {
        Product expectedProduct = Product.builder().id(ID_THREE).image(IMAGE).name(PLUMS).price(PRICE)
                .rating(RATING).description(DESCRIPTION).sizes(SIZES).sizeType(SizeTypes.PACKS)
                .productsReviews(REVIEW_LIST_PRODUCT_ONE).build();
        expectedProduct.setViews(new Views(expectedProduct, 3));
        product.setId(ID_THREE);
        when(productRepository.findById(ID_THREE)).thenReturn(Optional.of(expectedProduct));
        when(productRepository.save(product)).thenReturn(product);

        productService.save(product);

        assertAll(
                () -> assertThat(product).isNotNull(),
                () -> assertThat(product).isEqualTo(expectedProduct),
                () -> assertThat(product.getViews()).isNotNull(),
                () -> assertThat(product.getViews()).isEqualTo(expectedProduct.getViews()),
                () -> assertThat(product.getProductsReviews()).isNotNull(),
                () -> assertThat(product.getProductsReviews()).isEqualTo(expectedProduct.getProductsReviews())
        );
    }

    @Test
    void save_ThrowsException_ProductWithIncorrectId() {
        product.setId(4L);
        when(productRepository.findById(4L)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> productService.save(product)
        ).isInstanceOf(NoProductFoundException.class)
                .hasMessage("Could not find a product with specified id[%d]", product.getId());
    }

    @Test
    void shouldCalculateRating() {
        productService.setRatingForProduct(ID_ONE);

        verify(productRepository, times(1)).setRatingForProduct(ID_ONE);
    }


    @Test
    void getAll_ListWithTwoProducts_PageSizeTwo() {
        List<Product> products = Arrays.asList(PRODUCT_ONE, PRODUCT_TWO);
        when(productRepository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(products));

        Page<Product> result = productService.getAll(EMPTY_STRING, PageRequest.of(0, 2));

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isNotEmpty(),
                () -> assertThat(result.getSize()).isEqualTo(products.size())
        );
    }

    @Test
    void getAll_ListWithOneProduct_NameFilterAndPageSizeTwo() {
        when(productRepository.findByNameContainsIgnoreCase(PLUMS, PageRequest.of(0, 2))).thenReturn(new PageImpl<>(List.of(PRODUCT_TWO)));

        Page<Product> result = productService.getAll(PLUMS, PageRequest.of(0, 2));

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isNotEmpty(),
                () -> assertThat(result.getSize()).isEqualTo(1)
        );
    }

    @Test
    void getCountAll_NumberOfProducts_EmptyString() {
        final long expNumber = 3L;
        when(productRepository.count()).thenReturn(expNumber);

        long resultNumber = productService.getCountAll(EMPTY_STRING);

        assertThat(resultNumber).isEqualTo(expNumber);
    }

    @Test
    void getCountAll_NumberOfProducts_NotEmptyString() {
        final long expNumber = 1L;
        when(productRepository.countByNameContainsIgnoreCase(PLUMS)).thenReturn(expNumber);

        long resultNumber = productService.getCountAll(PLUMS);

        assertThat(resultNumber).isEqualTo(expNumber);
    }

    @Test
    void deleteById_DeletedProduct_CorrectId() {
        when(productRepository.findById(ID_THREE)).thenReturn(Optional.of(product));

        productService.deleteById(ID_THREE);

        verify(productRepository).delete(product);
    }

    @Test
    void deleteById_ThrowsException_IncorrectId() {
        when(productRepository.findById(ID_THREE)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> productService.deleteById(ID_THREE)
        ).isInstanceOf(NoProductFoundException.class)
                .hasMessage(String.format("Could not find a product with specified id[%d]", ID_THREE));
    }
}