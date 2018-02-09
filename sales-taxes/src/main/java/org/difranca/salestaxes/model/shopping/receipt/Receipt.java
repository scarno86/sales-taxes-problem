package org.difranca.salestaxes.model.shopping.receipt;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Receipt {
 
	private List<ReceiptItem> receiptDetails;
	
	private BigDecimal salesTaxes;
	
	private BigDecimal total;
	
}
