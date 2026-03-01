package com.projeto.pizzaria.demo.service;

import java.math.BigDecimal;
import java.util.UUID;

import com.projeto.pizzaria.demo.DTO.CreateOrderRequest;
import com.projeto.pizzaria.demo.DTO.OrderItemRequest;
import com.projeto.pizzaria.demo.entity.Order;
import com.projeto.pizzaria.demo.entity.OrderItem;
import com.projeto.pizzaria.demo.entity.Product;
import com.projeto.pizzaria.demo.entity.User;
import com.projeto.pizzaria.demo.enums.OrderStatus;
import com.projeto.pizzaria.demo.repository.OrderRepository;
import com.projeto.pizzaria.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public Order createOrder(CreateOrderRequest request, BigDecimal BigDecimal) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);

        BigDecimal orderTotal = java.math.BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            UUID productId = UUID.fromString(itemRequest.getProductId());
            Product product = productService.findByid(productId);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());


            orderItem.setPrice(product.getPrice());

            // total do item = price * quantity
            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            orderItem.setTotalPrice(itemTotal);


            order.addItem(orderItem);

            orderTotal = orderTotal.add(itemTotal);
        }

        order.setTotal(orderTotal);

        // cascade salva OrderItem automaticamente
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null && order.getStatus().canChangeTo(newStatus)) {
            order.setStatus(newStatus);
            return orderRepository.save(order);
        }

        return null;
    }

    public Order getOrderById(UUID id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}