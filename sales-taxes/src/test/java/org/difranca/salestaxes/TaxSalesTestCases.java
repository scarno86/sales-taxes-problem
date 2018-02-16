
package org.difranca.salestaxes;

import static org.assertj.core.api.Assertions.assertThat;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;
import org.difranca.salestaxes.services.receipt.IReceiptService;
import org.difranca.salestaxes.services.receipt.impl.ReceiptService;
import org.difranca.salestaxes.services.shopping.IShoppingBasketService;
import org.difranca.salestaxes.services.shopping.impl.ShoppingBasketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TaxSalesTestCases {

	private IReceiptService receiptService = new ReceiptService();
	private IShoppingBasketService shoppingService = new ShoppingBasketService();

	@Before
	public void initMock() {
		MockUtils.mockShoppingBasketService();
	}

	@DataProvider
	public static Object[][] createShoppingBasket() {
		return new Object[][] { { 1l, MockUtils.createReceipt.apply(1l) }, { 2l, MockUtils.createReceipt.apply(2l) },
				{ 3l, MockUtils.createReceipt.apply(3l) }, };
	}

	@Test
	@UseDataProvider(value = "createShoppingBasket")
	public void testReceiptDetails(Long idShoppingBasket, Receipt receiptExpected) {

		// load shoppingBasket
		ShoppingBasket shoppingBasket = shoppingService.loadBasketByIdBasket(idShoppingBasket);
		assertThat(shoppingBasket).as("Shopping Basket not null").isNotNull();

		Receipt calculatedReceipt = receiptService.calculateReceipt(shoppingBasket);
		assertThat(calculatedReceipt).as("Calculated Receipt is not null").isNotNull();

		// Print receipt details
		receiptService.printReceipt(calculatedReceipt);
		
		// n.b...comparing objects fields to fields because toEquals method has not been
		// overriden
		assertThat(calculatedReceipt).as("Calculated Receipt is equals to Expected")
				.isEqualToComparingFieldByFieldRecursively(receiptExpected);

		
	}

}