package com.orderRequest.domain.order.services;

import com.orderRequest.domain.order.builder.ItemBuilder;
import com.orderRequest.domain.order.dto.ItemDTO;
import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.entities.OrderEntity;
import com.orderRequest.domain.order.enums.ItemTypeEnum;
import com.orderRequest.domain.order.repositories.ItemRepository;
import com.orderRequest.domain.order.repositories.OrderRepository;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest
{

	@InjectMocks
	private ItemService itemService;
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private OrderRepository orderRepository;
	private ItemEntity itemEntity;
	private UUID itemId;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
		itemId = UUID.randomUUID();
		itemEntity = new ItemBuilder().aItem().withId(itemId).withName("Teste").withValue(new BigDecimal(10.00))
			.withType(ItemTypeEnum.PRODUCT).isActive(true).build();
	}

	@Test
	void shouldCreateItem()
	{
		ItemDTO itemDTO = new ItemDTO("Teste", BigDecimal.valueOf(10.00), ItemTypeEnum.PRODUCT,
			true);
		when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

		ItemEntity createdItem = itemService.createItem(itemDTO);

		assertNotNull(createdItem);
		assertEquals(itemEntity.getName(), createdItem.getName());
		assertEquals(0, itemEntity.getItemValue().compareTo(createdItem.getItemValue()));
		assertEquals(itemEntity.getItemType(), createdItem.getItemType());
		assertEquals(itemEntity.getActivated(), createdItem.getActivated());
		verify(itemRepository, times(1)).save(any(ItemEntity.class));
	}

	@Test
	public void shouldGetAItem() throws Exception {

		when(itemRepository.findItemEntitiesById(itemEntity.getId())).thenReturn(Optional.of(itemEntity));

		ItemEntity foundItem = itemService.getItemById(itemEntity.getId());

		assertEquals(itemEntity.getId(), foundItem.getId());
	}

	@Test
	public void shouldNotGetAItem() {

		when(itemRepository.findItemEntitiesById(itemId)).thenReturn(Optional.empty());

		assertThrows(Exception.class, () -> itemService.getItemById(itemId));
	}

	@Test
	public void shouldUpdateItem() throws Exception {
		ItemDTO updatedItemDTO = new ItemDTO("Teste Atualizado", BigDecimal.valueOf(20.00), ItemTypeEnum.SERVICE, false);

		when(itemRepository.findItemEntitiesById(itemId)).thenReturn(Optional.of(itemEntity));
		when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

		ItemEntity updatedItem = itemService.updateItem(itemId, updatedItemDTO);

		assertNotNull(updatedItem);
		assertEquals("Teste Atualizado", updatedItem.getName());
		assertEquals(0, updatedItem.getItemValue().compareTo(BigDecimal.valueOf(20.00)));
		assertEquals(ItemTypeEnum.SERVICE, updatedItem.getItemType());
		assertFalse(updatedItem.getActivated());

		verify(itemRepository, times(1)).save(any(ItemEntity.class));
	}
	@Test
	public void shouldDeleteAItem() throws Exception {
		when(itemRepository.findItemEntitiesById(itemId)).thenReturn(Optional.of(itemEntity));

		when(orderRepository.findByOrderItensId(itemId)).thenReturn(Collections.emptyList());

		String result = itemService.deleteItem(itemId);

		assertEquals("Item deletado com sucesso", result);

		verify(itemRepository, times(1)).delete(itemEntity);
	}

	@Test
	public void shouldNotDeleteItemAssossiatedToOrder() throws Exception {
		when(itemRepository.findItemEntitiesById(itemId)).thenReturn(Optional.of(itemEntity));

		List<OrderEntity> associatedOrders = Collections.singletonList(new OrderEntity());
		when(orderRepository.findByOrderItensId(itemId)).thenReturn(associatedOrders);

		String result = itemService.deleteItem(itemId);
		assertEquals("Item Não pode ser excluido pois está associado a um pedido", result);

		verify(itemRepository, never()).delete(any(ItemEntity.class));
	}
}


