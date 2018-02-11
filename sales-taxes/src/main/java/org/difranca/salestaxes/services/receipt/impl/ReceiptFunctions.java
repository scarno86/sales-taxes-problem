package org.difranca.salestaxes.services.receipt.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.model.shopping.receipt.ReceiptItem;
import org.difranca.salestaxes.services.receipt.strategy.SalesTaxStrategyContext;
import org.difranca.salestaxes.services.shopping.ShoppingBasketFunctions;

public class ReceiptFunctions {

	public static BiFunction<ShoppingBasketItem, SalesTaxStrategyContext, ReceiptItem> basketItemToReceiptItem = (
			shoppingBasketItem, salesTaxStrategyContext) ->  new ReceiptItem(shoppingBasketItem, salesTaxStrategyContext.executeStrategy(shoppingBasketItem));

	public static Function<ReceiptItem, BigDecimal> calculateTotalPriceItemByReceiptItem = receiptItem -> ShoppingBasketFunctions.calculateTotalShoppingPriceItem
			.apply(receiptItem.getItemBasket()).add(receiptItem.getSalesTax());

}
