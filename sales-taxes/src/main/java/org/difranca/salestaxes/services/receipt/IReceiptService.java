package org.difranca.salestaxes.services.receipt;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;

public interface IReceiptService {

	Receipt calculateReceipt(ShoppingBasket shoppingBasket);

	void printReceipt(Receipt receiptToPrint);
}
