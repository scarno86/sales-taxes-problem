package org.difranca.salestaxes.model.shopping;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingBasket {

	private Long idBasket;
	
	private String description;
	
	private List<ShoppingBasketItem> shoppingBasketItem;
	
}
