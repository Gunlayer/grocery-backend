package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.repositories.ViewsRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ViewsServiceImplTest {

    @Mock
    private ViewsRepository viewsRepository;

    @InjectMocks
    private ViewsServiceImpl viewsService;

    @Test
    void getMostViewed_ListOfProducts_CorrectNumber() {
        final int number = 2;
        Mockito.when(viewsRepository
                .findAllByNumberGreaterThanEqualOrderByNumberDescProduct_nameAsc(1L, PageRequest.of(0, number))
        ).thenReturn(List.of(mock(Views.class), mock(Views.class)));

        List<Views> actual = viewsService.getMostViewed(number);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isNotEmpty(),
                () -> assertThat(actual).hasSize(number)
        );
    }

    @Test
    void getMostViewed_throwsException_IncorrectNumber() {
        final int number = 0;

        assertThatThrownBy(() -> viewsService.getMostViewed(number))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Number of products must be at least 1");
    }

    @Test
    void increaseViewNumber_viewNumberWasIncreased_CorrectData() {
        final long number = 12;
        Views views = new Views(mock(Product.class), number);
        ArgumentCaptor<Views> viewsCaptor = ArgumentCaptor.forClass(Views.class);

        viewsService.increaseViewNumber(views);

        assertAll(
                () -> verify(viewsRepository).save(viewsCaptor.capture()),
                () -> assertThat(viewsCaptor.getValue().getNumber()).isEqualTo(number + 1)
        );
    }

}