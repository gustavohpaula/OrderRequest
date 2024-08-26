package com.orderRequest.domain.order.repositories;

import com.orderRequest.domain.order.entities.ItemEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>
{
	Optional<ItemEntity> findItemEntitiesById(UUID id);
}
