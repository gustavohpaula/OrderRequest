package com.orderRequest.domain.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController
{

	@PostMapping("/createOrder")
	public ResponseEntity<String> createOrder(){

		return new ResponseEntity<>("Pedido Criado", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getOrder(){

		return new ResponseEntity<>("Pedido", HttpStatus.FOUND);
	}

	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<String> updateOrder(){

		return new ResponseEntity<>("Pedido atualizado", HttpStatus.OK);
	}

	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<String> deleteOrder(){
		return new ResponseEntity<>("Pedido excluido", HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<String> getAllOrders(){

		return new ResponseEntity<>("Pedidos", HttpStatus.FOUND);
	}
}
