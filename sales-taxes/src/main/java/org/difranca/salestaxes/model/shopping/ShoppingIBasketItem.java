package org.difranca.salestaxes.model.shopping;


import org.difranca.salestaxes.model.goods.GoodItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingIBasketItem {

	private Integer quantity;
	
	private GoodItem item;
	
	
}
