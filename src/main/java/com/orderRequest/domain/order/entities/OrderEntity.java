package com.orderRequest.domain.order.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.orderRequest.domain.order.dto.OrderDTO;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@EqualsAndHashCode(of = "id")
public class OrderEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToMany
	@JoinTable(
		name = "order_items",
		joinColumns = @JoinColumn(name = "order_id"),
		inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<ItemEntity> orderItens;
	private BigDecimal discount;

	public OrderEntity(OrderDTO data){
		this.orderItens = data.getOrderItems();
		this.discount = data.getDiscount();
	}

	public void updateOrderData(OrderDTO data){
		this.orderItens = data.getOrderItems();
		this.discount = data.getDiscount();
	}
}
