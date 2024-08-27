package com.orderRequest.domain.order.repositories;

import com.orderRequest.domain.order.entities.OrderEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
	Optional<OrderEntity> findOrderEntitiesById(UUID id);

	List<OrderEntity> findByOrderItensId(UUID itemId);
}
