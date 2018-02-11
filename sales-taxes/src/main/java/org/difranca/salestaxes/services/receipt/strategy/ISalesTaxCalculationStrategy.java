package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;

public interface ISalesTaxCalculationStrategy {

	 BigDecimal calculateSalesTax(BigDecimal totalShoppingPrice);
	
}
