package org.difranca.salestaxes.services.receipt.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.model.shopping.receipt.ReceiptItem;
import org.difranca.salestaxes.services.receipt.strategy.SalesTaxFunctions;
import org.difranca.salestaxes.services.receipt.strategy.SalesTaxStrategyContext;
import org.difranca.salestaxes.services.shopping.impl.ShoppingBasketFunctions;

public class ReceiptFunctions {

	public static BiFunction<ShoppingBasketItem, SalesTaxStrategyContext, ReceiptItem> basketItemToReceiptItem = (
			shoppingBasketItem, salesTaxStrategyContext) -> {
																BigDecimal salesTaxes = salesTaxStrategyContext.executeStrategy(shoppingBasketItem);
																
																//apply rounding
																BigDecimal roundedTotalSalesTaxes = SalesTaxFunctions.roundingOff.apply(salesTaxes);
																
																return new ReceiptItem(shoppingBasketItem, roundedTotalSalesTaxes);
															};

	public static Function<ReceiptItem, BigDecimal> calculateTotalPriceItemByReceiptItem = receiptItem -> ShoppingBasketFunctions.calculateTotalShoppingPriceItem
			.apply(receiptItem.getItemBasket()).add(receiptItem.getSalesTax());

}
