package org.difranca.salestaxes.model.shopping.receipt;

import java.math.BigDecimal;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.services.shopping.impl.ShoppingBasketFunctions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceiptItem {

	private ShoppingBasketItem itemBasket;

	private BigDecimal salesTax;

	@Override
	public String toString() {
		return new StringBuilder(this.getItemBasket().toString())
				.append(ShoppingBasketFunctions.calculateTotalShoppingPriceItem.apply(itemBasket).toString())
				.append("\n").toString();
	}

}
