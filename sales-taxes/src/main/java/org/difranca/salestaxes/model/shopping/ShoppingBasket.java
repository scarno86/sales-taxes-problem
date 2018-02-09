package org.difranca.salestaxes.model.shopping;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingBasket {

	private Long idBasket;
	
	private String description;
	
	private List<ShoppingIBasketItem> basketItems;
	
}
