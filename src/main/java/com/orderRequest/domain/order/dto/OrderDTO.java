package com.orderRequest.domain.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO
{

	@NonNull
	private OrderItensDTO orderItens;
}
