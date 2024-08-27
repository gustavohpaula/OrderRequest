package com.orderRequest.domain.order.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderRequest.domain.order.dto.ItemDTO;
import com.orderRequest.domain.order.enums.ItemTypeEnum;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Entity(name = "item")
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private BigDecimal itemValue;
	private ItemTypeEnum itemType;
	private Boolean activated;

	@ManyToMany(mappedBy = "orderItens")
	@JsonIgnore
	private List<OrderEntity> orders;
	public ItemEntity(ItemDTO item){
		this.name = item.getName();
		this.itemValue = item.getValue();
		this.itemType = item.getItemType();
		this.activated = item.getActivated();
	}

	public void updateItemData(ItemDTO data){
		this.name = data.getName();
		this.itemValue = data.getValue();
		this.itemType = data.getItemType();
		this.activated = data.getActivated();
	}

	@Override
	public String toString()
	{
		return "ItemEntity{" + "id=" + id + ", name='" + name + '\'' + ", itemValue=" + itemValue
			+ ", itemType=" + itemType + '}';
	}
}
