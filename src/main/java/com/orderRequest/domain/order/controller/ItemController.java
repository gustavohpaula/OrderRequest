package com.orderRequest.domain.order.controller;

import com.orderRequest.domain.order.dto.ItemDTO;
import com.orderRequest.domain.order.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController
{

	@PostMapping("/createItem")
	public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO){

		return new ResponseEntity<>(itemDTO.toString(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<String> getItem(@RequestParam Long id){

		return new ResponseEntity<>(id.toString(), HttpStatus.FOUND);
	}

	@PutMapping("/updateItem")
	public ResponseEntity<String> updateItem(@RequestParam Long id, @RequestBody ItemDTO itemDTO){

		return new ResponseEntity<>(itemDTO.toString() + " " +id.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteItem")
	public ResponseEntity<String> deleteItem(@RequestParam Long id){
		return new ResponseEntity<>(id.toString(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<String> getAllItems(){

		return new ResponseEntity<>("items", HttpStatus.FOUND);
	}
}
