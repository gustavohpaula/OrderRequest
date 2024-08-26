package com.orderRequest.domain.order.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItensDTO
{

	@NonNull
	private List<ProductServiceDTO> itensList;
}
