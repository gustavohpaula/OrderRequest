package com.orderRequest.domain.order.controller;

import com.orderRequest.domain.order.dto.OrderDTO;
import com.orderRequest.domain.order.entities.OrderEntity;
import com.orderRequest.domain.order.services.OrderService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController
{

	@Autowired
	private OrderService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDTO orderDTO){

		OrderEntity order = this.orderService.createOrder(orderDTO);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<OrderEntity> getOrder(@RequestParam UUID id) throws Exception
	{

		OrderEntity order = this.orderService.getOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<OrderEntity> updateOrder(@RequestParam UUID id, @RequestBody OrderDTO orderDTO)
		throws Exception
	{

		OrderEntity order = this.orderService.updateOrder(id, orderDTO);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@DeleteMapping("/deleteOrder")
	public ResponseEntity<String> deleteOrder(@RequestParam UUID id) throws Exception
	{
		String returnMessage = this.orderService.deleteOrder(id);
		return new ResponseEntity<>(returnMessage, HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<Page<OrderEntity>> getAllOrders(int page, int size){

		Page<OrderEntity> orders = this.orderService.getAllOrders(page, size);
		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}
}
