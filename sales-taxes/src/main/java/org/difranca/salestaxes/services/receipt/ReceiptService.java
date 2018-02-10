package org.difranca.salestaxes.services.receipt;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;
import org.difranca.salestaxes.model.shopping.receipt.ReceiptItem;

public class ReceiptService implements IReceiptService {

	
	private Function<ShoppingBasketItem, ReceiptItem> basketItemToReceiptItemFunction = shoppingBasketItem -> new ReceiptItem(shoppingBasketItem, null);
	
	private Function<ShoppingBasketItem,BigDecimal> calculateTotalShoppingPriceItem = shoppingBasketItem -> shoppingBasketItem.getItem().getUnityPrice()*shoppingBasketItem.getQuantity();
	
	private Function<ReceiptItem, BigDecimal> totalPriceByReceiptItemFunction = receiptItem -> receiptItem
	
	@Override
	public Receipt calculateReceipt(ShoppingBasket shoppingBasket) {
		
		String description = shoppingBasket.getDescription();
		
		List<ReceiptItem> receiptItems = shoppingBasket.getShoppingBasketItem().stream()//
					  .map(basketItemToReceiptItemFunction)//
					  .collect(Collectors.toList());
		
		BigDecimal salesTax = receiptItems.stream()//
									.map(receiptItem -> receiptItem.getSalesTax())//
								    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal totalPrice = receiptItems.stream()//
								.map(receiptItem -> receiptItem.getSalesTax())//
							    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Receipt receipt = new Receipt(description, receiptItems, salesTax, totalPrice);
		
		return receipt;
	}

	
	
	@Override
	public void printReceipt(Receipt receiptToPrint) {
		
	}

}
