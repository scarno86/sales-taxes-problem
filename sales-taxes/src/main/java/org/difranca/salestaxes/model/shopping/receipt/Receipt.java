package org.difranca.salestaxes.model.shopping.receipt;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Receipt {
 
	
	private Long idShoppingBasket;
	
	private String description;
	
	private List<ReceiptItem> receiptDetails;
	
	private BigDecimal salesTaxes;
	
	private BigDecimal total;

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder("Output ");
		builder.append(this.getIdShoppingBasket());
		this.receiptDetails.stream().forEach(receiptItem -> builder.append(receiptItem).toString());
		builder.append("Sales Taxes: "+this.getSalesTaxes());
		builder.append("Total: "+this.getTotal());
		return  builder.toString();
	}

		
}
