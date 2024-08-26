package com.orderRequest.domain.order.controller;

import com.orderRequest.domain.order.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController
{

	@PostMapping("/createOrder")
	public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO){

		return new ResponseEntity<>(orderDTO.toString(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<String> getOrder(@RequestParam Long id){

		return new ResponseEntity<>(id.toString(), HttpStatus.FOUND);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<String> updateOrder(@RequestParam Long id, @RequestBody OrderDTO orderDTO){

		return new ResponseEntity<>(orderDTO.toString() + " " +id.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteOrder")
	public ResponseEntity<String> deleteOrder(@RequestParam Long id){
		return new ResponseEntity<>(id.toString(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<String> getAllOrders(){

		return new ResponseEntity<>("Pedidos", HttpStatus.FOUND);
	}
}
