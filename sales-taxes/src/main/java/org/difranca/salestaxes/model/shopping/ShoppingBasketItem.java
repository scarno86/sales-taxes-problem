package org.difranca.salestaxes.model.shopping;

import org.difranca.salestaxes.model.goods.GoodItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingBasketItem {

	private Integer quantity;

	private GoodItem item;

	@Override
	public String toString() {
		return new StringBuffer("").append(this.quantity).append(item.toString()).toString();
	}

}
