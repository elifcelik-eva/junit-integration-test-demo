package com.elifcelik.testlab.unitTestCases.hardDemo;

public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, PaymentService paymentService, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public Order placeOrder(Order order){
        boolean paymentSuccess = paymentService.processPayment(order.getTotalAmount());
        if (!paymentSuccess) {
            throw new PaymentFailedException("Payment failed");
        }
        Order savedOrder = orderRepository.save(order);
        notificationService.sendConfirmation(savedOrder);
        return savedOrder;
    }
}
