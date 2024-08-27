package com.orderRequest.domain.order.services;

import com.orderRequest.domain.order.dto.OrderDTO;
import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.entities.OrderEntity;
import com.orderRequest.domain.order.enums.ItemTypeEnum;
import com.orderRequest.domain.order.repositories.OrderRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{

	@Autowired
	private OrderRepository repository;

	@Autowired
	ItemService itemService;

	public OrderEntity createOrder(OrderDTO data)
	{
		OrderEntity newOrder = new OrderEntity(data);
		newOrder.getOrderItens().removeIf(item -> !item.getActivated());
		newOrder = this.calculateTotalValue(newOrder);
		this.saveOrder(newOrder);
		return newOrder;
	}

	public OrderEntity getOrderById(UUID id) throws Exception
	{
		return this.repository.findOrderEntitiesById(id)
			.orElseThrow(() -> new Exception("order not Found"));
	}

	public Page<OrderEntity> getAllOrders(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return this.repository.findAll(pageable);
	}

	public OrderEntity updateOrder(UUID id, OrderDTO data) throws Exception
	{
		OrderEntity order = this.getOrderById(id);
		order.getOrderItens().removeIf(item -> !item.getActivated());
		order = this.calculateTotalValue(order);
		order.updateOrderData(data);
		this.saveOrder(order);
		return order;
	}

	public String deleteOrder(UUID id) throws Exception
	{
		OrderEntity order = this.getOrderById(id);
		this.removeOrder(order);
		return "Pedido deletado com sucesso";
	}

	public void saveOrder(OrderEntity order)
	{
		this.repository.save(order);
	}

	public void removeOrder(OrderEntity order)
	{
		this.repository.delete(order);
	}

	public OrderEntity calculateTotalValue(OrderEntity order)
	{
		BigDecimal totalValue = BigDecimal.ZERO;

		for (ItemEntity item : order.getOrderItens())
		{
			if (isProduct(item) && isValidSituation(order))
			{
				BigDecimal itemValue = item.getItemValue();
				BigDecimal discount = order.getDiscountPercentage();

				BigDecimal discountAmount = itemValue.multiply(discount);
				BigDecimal finalValue = itemValue.subtract(discountAmount);

				totalValue = totalValue.add(finalValue);
			}
			else{

				totalValue = totalValue.add(item.getItemValue());
			}
		}

		order.setTotalValue(totalValue);
		return order;
	}

	public boolean isValidSituation(OrderEntity order)
	{
		return order.getSituation();
	}

	public boolean isProduct(ItemEntity item)
	{
		return item.getItemType() == ItemTypeEnum.PRODUCT;
	}
}
