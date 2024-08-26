package com.orderRequest.domain.order.entities;

import com.orderRequest.domain.order.enums.ItemTypeEnum;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity(name = "item")
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private BigDecimal value;
	private ItemTypeEnum itemType;
}
