package com.orderRequest.domain.order.controller;

import com.orderRequest.domain.order.dto.ItemDTO;
import com.orderRequest.domain.order.dto.OrderDTO;
import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.services.ItemService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController
{
	@Autowired
	private ItemService itemService;

	@PostMapping("/createItem")
	public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDTO itemDTO){

		ItemEntity item = this.itemService.createItem(itemDTO);
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<String> getItem(@RequestParam UUID id) throws Exception
	{

		try
		{
			ItemEntity item = this.itemService.getItemById(id);
			return new ResponseEntity<>(item.toString(), HttpStatus.FOUND);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateItem")
	public ResponseEntity<ItemEntity> updateItem(@RequestParam UUID id, @RequestBody ItemDTO itemDTO)
		throws Exception
	{
		ItemEntity item = this.itemService.updateItem(id, itemDTO);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@DeleteMapping("/deleteItem")
	public ResponseEntity<String> deleteItem(@RequestParam UUID id) throws Exception
	{
		String returnMessage = this.itemService.deleteItem(id);
		return new ResponseEntity<>(returnMessage, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<String> getAllItems(){

		return new ResponseEntity<>("items", HttpStatus.FOUND);
	}
}
