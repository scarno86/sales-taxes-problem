package org.difranca.salestaxes.services.receipt.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;
import org.difranca.salestaxes.model.shopping.receipt.ReceiptItem;
import org.difranca.salestaxes.services.receipt.IReceiptService;
import org.difranca.salestaxes.services.receipt.strategy.SalesTaxStrategyContext;

public class ReceiptService implements IReceiptService {

	
	private SalesTaxStrategyContext salesTaxStrategyContext = new SalesTaxStrategyContext();
	
	@Override
	public Receipt calculateReceipt(ShoppingBasket shoppingBasket) {
		
		String description = shoppingBasket.getDescription();
		
		List<ReceiptItem> receiptItems = shoppingBasket.getShoppingBasketItem().stream()//
					  .map(shoppingBasketItem -> ReceiptFunctions.basketItemToReceiptItem.apply(shoppingBasketItem, salesTaxStrategyContext))//
					  .collect(Collectors.toList());
		
		BigDecimal salesTax = receiptItems.stream()//
									.map(receiptItem -> receiptItem.getSalesTax())//
								    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal totalPrice = receiptItems.stream()//
								.map(receiptItem -> ReceiptFunctions.calculateTotalPriceItemByReceiptItem.apply(receiptItem))//
							    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return new Receipt(description, receiptItems, salesTax, totalPrice);
		
	}

	@Override
	public void printReceipt(Receipt receiptToPrint) {
		
	}

}