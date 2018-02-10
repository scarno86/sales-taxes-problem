package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;

import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;

public interface ISalesTaxCalculationStrategy {

	 BigDecimal calculateSalesTax(ShoppingBasketItem shoppingBasketItem);
	
}
