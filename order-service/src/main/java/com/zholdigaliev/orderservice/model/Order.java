package com.zholdigaliev.orderservice.model;

import com.zholdigaliev.orderservice.exception.InvalidStatusTransitionException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status orderStatus = Status.NEW;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String orderAddress;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItems = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.orderedAt = LocalDateTime.now();
    }

    public void transitionTo(Status newStatus) {
        if(!this.orderStatus.canTransitionTo(newStatus)) {
            throw new InvalidStatusTransitionException(
                    "Order: " +
                    this.id + " status: " +
                    this.orderStatus  + " to " +
                    newStatus + " not allowed." + " Allowed: " +
                    this.orderStatus.getAllowedTransitions()
            );
        }
        this.orderStatus = newStatus;
    }
}
