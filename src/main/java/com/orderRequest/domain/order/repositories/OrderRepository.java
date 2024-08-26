package com.orderRequest.domain.order.repositories;

import com.orderRequest.domain.order.entities.OrderEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
	Optional<OrderEntity> findOrderEntitiesById(UUID id);
}
