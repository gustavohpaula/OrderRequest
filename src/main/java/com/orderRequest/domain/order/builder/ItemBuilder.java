package com.orderRequest.domain.order.builder;

import com.orderRequest.domain.order.entities.ItemEntity;
import com.orderRequest.domain.order.enums.ItemTypeEnum;
import java.math.BigDecimal;
import java.util.UUID;

public class ItemBuilder {

	private ItemEntity itemEntity;

	public ItemBuilder() {
		this.itemEntity = new ItemEntity();
	}

	public ItemBuilder aItem() {
		return new ItemBuilder();
	}

	public ItemBuilder withId(UUID id) {
		itemEntity.setId(id);
		return this;
	}

	public ItemBuilder withName(String name) {
		itemEntity.setName(name);
		return this;
	}

	public ItemBuilder withValue(BigDecimal value) {
		itemEntity.setItemValue(value);
		return this;
	}

	public ItemBuilder withType(ItemTypeEnum itemTypeEnum) {
		itemEntity.setItemType(itemTypeEnum);
		return this;
	}

	public ItemBuilder isActive(boolean value) {
		itemEntity.setActivated(value);
		return this;
	}

	public ItemEntity build() {
		return itemEntity;
	}
}
