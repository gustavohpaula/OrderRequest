package com.orderRequest.domain.order.dto;

import com.orderRequest.domain.order.enums.ItemTypeEnum;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO
{

	@NonNull
	private String name;

	@NonNull
	private BigDecimal value;

	@NonNull
	private ItemTypeEnum itemType;

	@NonNull
	private Boolean activated;
}
