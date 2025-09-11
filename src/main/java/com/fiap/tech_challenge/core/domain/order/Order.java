package com.fiap.tech_challenge.core.domain.order;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.restaurant.Menu;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Restaurant restaurant;
    private Customer customer;
    private List<Menu> items = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime canceledAt;
    private Double orderPrice;
    private boolean paid;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    public Order(Long id, Restaurant restaurant,
                 Customer customer, List<Menu> items,
                 LocalDateTime createdAt, LocalDateTime confirmedAt,
                 LocalDateTime deliveredAt, LocalDateTime canceledAt,
                 Double orderPrice, boolean paid,
                 PaymentMethod paymentMethod, OrderStatus orderStatus) {
        this.id = id;
        this.restaurant = restaurant;
        this.customer = customer;
        this.items = items;
        this.createdAt = createdAt;
        this.confirmedAt = confirmedAt;
        this.deliveredAt = deliveredAt;
        this.canceledAt = canceledAt;
        this.orderPrice = orderPrice;
        this.paid = paid;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Menu> getItems() {
        return items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public LocalDateTime getCanceledAt() {
        return canceledAt;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
