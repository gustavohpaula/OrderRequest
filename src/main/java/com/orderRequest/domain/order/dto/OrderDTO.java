package com.orderRequest.domain.order.dto;

import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.entities.OrderEntity;
import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO
{

	@NonNull
	private List<ItemEntity> orderItems;
	private BigDecimal discountPercentage;
	private Boolean situation;
}
