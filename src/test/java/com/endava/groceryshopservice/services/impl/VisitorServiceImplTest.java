package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Visitor;
import com.endava.groceryshopservice.repositories.VisitorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.endava.groceryshopservice.utils.TestConstants.VISITOR_ID;
import static com.endava.groceryshopservice.utils.VisitorUtils.VISITOR_LIST;
import static com.endava.groceryshopservice.utils.VisitorUtils.VISITOR_ONE;
import static com.endava.groceryshopservice.utils.VisitorUtils.VISITOR_REQUEST_DTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceImplTest {
    @Mock
    private VisitorRepository visitorRepository;

    @InjectMocks
    private VisitorServiceImpl visitorService;

    @Captor
    ArgumentCaptor<Visitor> visitorCaptor;

    @Test
    void shouldAddVisitor(){
        when(visitorRepository.findByVisitorId(VISITOR_ID)).thenReturn(null);
        when(visitorRepository.save(visitorCaptor.capture())).thenReturn(visitorCaptor.capture());

        visitorService.addVisitor(VISITOR_REQUEST_DTO);

        assertEquals(VISITOR_ONE, visitorCaptor.getValue());
    }

    @Test
    void shouldDeleteVisitor(){
        when(visitorRepository.findByVisitorId(VISITOR_ID)).thenReturn(VISITOR_ONE);
        doNothing().when(visitorRepository).delete(visitorCaptor.capture());

        visitorService.deleteVisitor(VISITOR_ID);

        assertEquals(VISITOR_ONE, visitorCaptor.getValue());
    }

    @Test
    void shouldFindVisitors(){
        when(visitorRepository.findByAddingDateAfter(LocalDate.now().minusDays(7))).thenReturn(VISITOR_LIST);

        List<Visitor> expected =  visitorService.findVisitors();

        assertEquals(expected, VISITOR_LIST);
    }
}