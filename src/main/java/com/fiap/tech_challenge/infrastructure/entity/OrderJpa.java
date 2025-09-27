package com.fiap.tech_challenge.infrastructure.entity;

import com.fiap.tech_challenge.core.domain.restaurant.Menu;
import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.infrastructure.entity.enums.OrderStatus;
import com.fiap.tech_challenge.infrastructure.entity.enums.PaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantJpa restaurantJpa;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerJpa customerJpa;
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<MenuJpa> items = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime canceledAt;
    private Double orderPrice;
    private boolean paid;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    public OrderJpa(Long id, RestaurantJpa restaurantJpa, CustomerJpa customerJpa, LocalDateTime createdAt, LocalDateTime confirmedAt, LocalDateTime deliveredAt, LocalDateTime canceledAt, Double orderPrice, boolean paid, PaymentMethod paymentMethod, OrderStatus orderStatus) {
        this.id = id;
        this.restaurantJpa = restaurantJpa;
        this.customerJpa = customerJpa;
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

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantJpa getRestaurantJpa() {
        return restaurantJpa;
    }

    public void setRestaurantJpa(RestaurantJpa restaurantJpa) {
        this.restaurantJpa = restaurantJpa;
    }

    public CustomerJpa getCustomerJpa() {
        return customerJpa;
    }

    public void setCustomerJpa(CustomerJpa customerJpa) {
        this.customerJpa = customerJpa;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public LocalDateTime getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(LocalDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderJpa{" +
                "id=" + id +
                ", restaurantJpa=" + restaurantJpa +
                ", customerJpa=" + customerJpa +
                ", createdAt=" + createdAt +
                ", confirmedAt=" + confirmedAt +
                ", deliveredAt=" + deliveredAt +
                ", canceledAt=" + canceledAt +
                ", orderPrice=" + orderPrice +
                ", paid=" + paid +
                ", paymentMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
