package org.difranca.salestaxes.services.shopping.impl;

import java.math.BigDecimal;
import java.util.function.Function;

import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;

public class ShoppingBasketFunctions {

	public static Function<ShoppingBasketItem, BigDecimal> calculateTotalShoppingPriceItem = shoppingBasketItem -> shoppingBasketItem
			.getItem().getUnityPrice().multiply(new BigDecimal(shoppingBasketItem.getQuantity()));

}
