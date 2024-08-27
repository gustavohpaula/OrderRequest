package com.orderRequest.domain.order.services;

import static org.mockito.Mockito.when;

import com.orderRequest.domain.order.builder.ItemBuilder;
import com.orderRequest.domain.order.builder.OrderBuilder;
import com.orderRequest.domain.order.dto.OrderDTO;
import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.entities.OrderEntity;
import com.orderRequest.domain.order.enums.ItemTypeEnum;
import com.orderRequest.domain.order.repositories.ItemRepository;
import com.orderRequest.domain.order.repositories.OrderRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest
{
	@InjectMocks
	private OrderService orderService;
	@InjectMocks
	private ItemService itemService;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private ItemRepository itemRepository;

	private OrderEntity orderEntity;
	private UUID orderId;
	List<ItemEntity> items = new ArrayList<>();

	@BeforeEach
	void setUp()
	{
		orderId = UUID.randomUUID();

		items.add(new ItemBuilder().aItem().withId(UUID.randomUUID()).withName("Teste Produto Ativo")
			.withValue(new BigDecimal(10.00)).withType(ItemTypeEnum.PRODUCT).isActive(true).build());
		items.add(new ItemBuilder().aItem().withId(UUID.randomUUID()).withName("Teste Produto Inativo")
			.withValue(new BigDecimal(10.00)).withType(ItemTypeEnum.PRODUCT).isActive(false).build());
		items.add(new ItemBuilder().aItem().withId(UUID.randomUUID()).withName("Teste Serviço")
			.withValue(new BigDecimal(10.00)).withType(ItemTypeEnum.SERVICE).isActive(true).build());

		orderEntity = new OrderBuilder().aOrder().withId(orderId).withOrderItems(items)
			.withDiscountPercentage(new BigDecimal(0.5)).withSituation(true).build();

	}

	@Test
	void shouldCreateOrder()
	{
		when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

		OrderDTO orderDTO = new OrderDTO(items, new BigDecimal(0.5), true);

		OrderEntity createdOrder = orderService.createOrder(orderDTO);

		assertNotNull(createdOrder);
		assertEquals(orderEntity.getOrderItens(), createdOrder.getOrderItens());
		assertEquals(0, orderEntity.getDiscountPercentage().compareTo(createdOrder.getDiscountPercentage()));
		assertEquals(orderEntity.getSituation(), createdOrder.getSituation());
		verify(orderRepository, times(1)).save(any(OrderEntity.class));
	}

	@Test
	void shouldCreateOrderWithSituationFalse()
	{
		when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
		orderEntity.setSituation(false);
		OrderDTO orderDTO = new OrderDTO(items, new BigDecimal(0.5), false);

		OrderEntity createdOrder = orderService.createOrder(orderDTO);

		assertNotNull(createdOrder);
		assertEquals(orderEntity.getOrderItens(), createdOrder.getOrderItens());
		assertEquals(orderEntity.getDiscountPercentage(), createdOrder.getDiscountPercentage());
		assertEquals(orderEntity.getSituation(), createdOrder.getSituation());
		verify(orderRepository, times(1)).save(any(OrderEntity.class));
	}

	@Test
	void shouldFindAOrder() throws Exception
	{
		when(orderRepository.findOrderEntitiesById(orderId)).thenReturn(Optional.of(orderEntity));

		OrderEntity foundOrder = orderService.getOrderById(orderId);

		assertNotNull(foundOrder);
		assertEquals(orderId, foundOrder.getId());
		verify(orderRepository, times(1)).findOrderEntitiesById(orderId);
	}

	@Test
	void shouldNotFindAOrder()
	{
		when(orderRepository.findOrderEntitiesById(orderId)).thenReturn(Optional.empty());

		Exception exception = assertThrows(Exception.class, () -> {
			orderService.getOrderById(orderId);
		});

		assertEquals("order not Found", exception.getMessage());
		verify(orderRepository, times(1)).findOrderEntitiesById(orderId);
	}

	@Test
	void shouldUpdateOrder() throws Exception
	{
		when(orderRepository.findOrderEntitiesById(orderId)).thenReturn(Optional.of(orderEntity));
		when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

		OrderDTO orderDTO = new OrderDTO(items, new BigDecimal(10.00), true);

		OrderEntity updatedOrder = orderService.updateOrder(orderId, orderDTO);

		assertNotNull(updatedOrder);
		assertEquals(orderDTO.getDiscountPercentage(), updatedOrder.getDiscountPercentage());
		assertEquals(orderDTO.getSituation(), updatedOrder.getSituation());
		verify(orderRepository, times(1)).findOrderEntitiesById(orderId);
		verify(orderRepository, times(1)).save(any(OrderEntity.class));
	}

	@Test
	void shouldDeleteOrder() throws Exception
	{
		when(orderRepository.findOrderEntitiesById(orderId)).thenReturn(Optional.of(orderEntity));

		String result = orderService.deleteOrder(orderId);

		assertEquals("Pedido deletado com sucesso", result);
		verify(orderRepository, times(1)).findOrderEntitiesById(orderId);
		verify(orderRepository, times(1)).delete(orderEntity);
	}

	@Test
	void shouldCalculateTotalValueWithDiscount() {
		orderEntity.getOrderItens().removeIf(item -> !item.getActivated());
		OrderEntity resultOrder = orderService.calculateTotalValue(orderEntity);

		assertNotNull(resultOrder);
		assertEquals(0, new BigDecimal(15.00).compareTo(resultOrder.getTotalValue()));
	}

	@Test
	void shouldCalculateTotalValueWithoutDiscount() {

		orderEntity.setSituation(false);
		orderEntity.getOrderItens().removeIf(item -> !item.getActivated());
		OrderEntity resultOrder = orderService.calculateTotalValue(orderEntity);

		assertNotNull(resultOrder);
		assertEquals(0, new BigDecimal(20.00).compareTo(resultOrder.getTotalValue()));
	}

}