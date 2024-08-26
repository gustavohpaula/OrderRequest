package com.orderRequest.domain.order.entities;

import jakarta.persistence.*;
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
	@ManyToMany(mappedBy = "item")
	private List<ItemEntity> orderItens;

}
