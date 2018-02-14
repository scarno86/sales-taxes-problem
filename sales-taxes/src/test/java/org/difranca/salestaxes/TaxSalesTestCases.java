package org.difranca.salestaxes;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;
import org.difranca.salestaxes.model.shopping.receipt.Receipt;
import org.difranca.salestaxes.services.receipt.IReceiptService;
import org.difranca.salestaxes.services.receipt.impl.ReceiptService;
import org.difranca.salestaxes.services.shopping.IShoppingBasketService;
import org.difranca.salestaxes.services.shopping.impl.ShoppingBasketService;
import org.junit.BeforeClass;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;


public class TaxSalesTestCases {

	private IReceiptService receiptService;
	private IShoppingBasketService shoppingService;
	

	@BeforeClass
	public void initService() {
		this.receiptService = new ReceiptService();
		this.shoppingService = new ShoppingBasketService();
	}
	

	@DataProvider
	public static Object[][] createShoppingBasket() {
		return new Object[][] {
					{1L, MockUtils.createReceipt.apply(1L) },
					//{2L, MockUtils.createReceipt.apply(2)   },
					//{3L, MockUtils.createReceipt.apply(3L)  },
				};
	}

	@Test
	@UseDataProvider(value="createShoppingBasket")
	public void testReceiptDetails(Long idShoppingBasket, Receipt receiptExpected) {
		
		//load  shoppingBasket
		ShoppingBasket shoppingBasket = shoppingService.loadBasketByIdBasket(idShoppingBasket);
		assertThat(shoppingBasket).as("Shopping Basket not null").isNotNull();
		
		Receipt calculatedReceipt = receiptService.calculateReceipt(shoppingBasket);
		assertThat(calculatedReceipt).as("Calculated Receipt is not null").isNotNull();
		
		//n.b...comparing objects fields to fields because toEquals method has not been overriden
		assertThat(calculatedReceipt).as("Calculated Receipt is equals to Expected").isEqualToComparingFieldByField(receiptExpected);
		
		//Print receipt details
		receiptService.printReceipt(calculatedReceipt);
	}

}