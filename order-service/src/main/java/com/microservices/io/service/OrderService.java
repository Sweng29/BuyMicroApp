package com.microservices.io.service;

import com.microservices.io.dto.OrderDTO;
import com.microservices.io.dto.request.OrderRequest;
import com.microservices.io.mapper.OrderMapper;
import com.microservices.io.model.Order;
import com.microservices.io.model.OrderItem;
import com.microservices.io.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .isActive(Boolean.TRUE)
                .build();
        List<OrderItem> orderItemList = processAndBuildOrderItems(orderRequest.getOrderItems(), order);
        order.setOrderItems(orderItemList);
        order.setTotalAmount(BigDecimal.valueOf(orderItemList.stream()
                .mapToDouble(orderItem -> orderItem.getTotalPrice().doubleValue()).sum()));
        order.setTotalDiscount(order.getTotalAmount()
                .min(BigDecimal.valueOf(orderItemList.stream()
                .mapToDouble(orderItem -> orderItem.getTotalDiscount().doubleValue()).sum())));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private List<OrderItem> processAndBuildOrderItems(List<OrderRequest.OrderItemRequest> orderItems, Order order) {
        return orderItems.stream()
                .map(orderItemRequest -> buildOrderItem(orderItemRequest, order))
                .collect(Collectors.toList());
    }

    private OrderItem buildOrderItem(OrderRequest.OrderItemRequest orderItemRequest, Order order) {
        return OrderItem.builder()
                .order(order)
                .isActive(Boolean.TRUE)
                .discountPerItem(orderItemRequest.getDiscountPerItem())
                .itemUnitPrice(orderItemRequest.getItemUnitPrice())
                .quantity(orderItemRequest.getQuantity())
                .skuCode(orderItemRequest.getSkuCode())
                .totalPrice(orderItemRequest.getItemUnitPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())))
                .totalDiscount(orderItemRequest.getItemUnitPrice()
                        .min(orderItemRequest.getDiscountPerItem()
                                .multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))))
                .build();
    }

    public OrderDTO findOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDto).orElse(null);
    }

    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }
}
