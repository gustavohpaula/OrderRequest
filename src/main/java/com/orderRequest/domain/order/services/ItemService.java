package com.orderRequest.domain.order.services;

import com.orderRequest.domain.order.dto.ItemDTO;
import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.repositories.ItemRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{

	@Autowired
	private ItemRepository repository;

	public ItemEntity createItem(ItemDTO data){
		ItemEntity newItem = new ItemEntity(data);
		this.saveItem(newItem);
		return newItem;
	}

	public ItemEntity getItemById(UUID id) throws Exception
	{
		return this.repository.findItemEntitiesById(id).orElseThrow(() -> new Exception("Item not Found"));
	}

	public ItemEntity updateItem(UUID id, ItemDTO data) throws Exception
	{
		ItemEntity item = this.getItemById(id);
		item.updateItemData(data);
		this.saveItem(item);
		return item;
	}
	public void saveItem(ItemEntity item){
		this.repository.save(item);
	}

	public String deleteItem(UUID id) throws Exception
	{
		ItemEntity item = this.getItemById(id);
		this.removeItem(item);
		return "Item deletado com sucesso";
	}

	public void removeItem(ItemEntity item){
		this.repository.delete(item);
	}


}
