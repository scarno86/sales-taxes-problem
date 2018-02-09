package org.difranca.salestaxes.model.shopping.receipt;


import java.math.BigDecimal;

import org.difranca.salestaxes.model.shopping.ShoppingIBasketItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptItem {

	
	private ShoppingIBasketItem itemBasket;
	
	private BigDecimal totalPrice;
}
