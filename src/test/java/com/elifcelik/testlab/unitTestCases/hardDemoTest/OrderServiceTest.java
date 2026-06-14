package com.elifcelik.testlab.unitTestCases.hardDemoTest;

import com.elifcelik.testlab.unitTestCases.hardDemo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private NotificationService notificationService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void sutUp(){
        order = new Order(1L, 100);
    }

    @Test
    void placeOrder_whenPaymentSuccess_shouldSaveAndNotify(){
        when(paymentService.processPayment(100)).thenReturn(true);
        when(orderRepository.save(order)).thenReturn(order);
        orderService.placeOrder(order);
        verify(orderRepository).save(order);
        verify(paymentService).processPayment(100);
        verify(notificationService).sendConfirmation(order);
    }

    @Test
    void placeOrder_whenPaymentFails_shouldThrowException(){
        when(paymentService.processPayment(100)).thenReturn(false);
        assertThrows(PaymentFailedException.class, ()-> orderService.placeOrder(order));
    }

    @Test
    void placeOrder_whenPaymentFails_shouldNotSendNotification(){
        when(paymentService.processPayment(100)).thenReturn(false);
        assertThrows(PaymentFailedException.class, ()-> orderService.placeOrder(order));
        verify(notificationService, never()).sendConfirmation(order);
    }

}
