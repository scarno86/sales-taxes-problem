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
 
	private String description;
	
	private List<ReceiptItem> receiptDetails;
	
	private BigDecimal salesTaxes;
	
	private BigDecimal total;

		
}
