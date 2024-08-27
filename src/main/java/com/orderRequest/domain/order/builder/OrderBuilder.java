package com.orderRequest.domain.order.builder;

import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.entities.OrderEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderBuilder
{

	private OrderEntity orderEntity;

	public OrderBuilder(){
		this.orderEntity = new OrderEntity();
	}

	public OrderBuilder aOrder(){
		return new OrderBuilder();
	}
	public OrderBuilder withId(UUID id){
		orderEntity.setId(id);
		return this;
	}

	public OrderBuilder withOrderItems(List<ItemEntity> items){
		orderEntity.setOrderItens(items);
		return this;
	}
	public OrderBuilder withDiscountPercentage(BigDecimal discountPercentage){
		orderEntity.setDiscountPercentage(discountPercentage);
		return this;
	}

	public OrderBuilder withTotalValue(BigDecimal totalValue){
		orderEntity.setTotalValue(totalValue);
		return this;
	}

	public OrderBuilder withSituation(boolean situation){
		orderEntity.setSituation(situation);
		return this;
	}

	public OrderEntity build(){
		return orderEntity;
	}
}
