package com.orderRequest.domain.order.dto;

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
	private List<String> orderItensID;
	private BigDecimal discount;
}
