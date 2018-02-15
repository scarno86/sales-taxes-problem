package org.difranca.salestaxes.services.receipt.strategy.impl;

import java.math.BigDecimal;

import org.difranca.salestaxes.services.receipt.strategy.ISalesTaxCalculationStrategy;

public class BasicSalesTaxCalculationStrategy implements ISalesTaxCalculationStrategy {

	private static final BigDecimal BASIC_TAX_RATE = new BigDecimal("0.1");
	
	@Override
	public BigDecimal calculateSalesTax(BigDecimal totalShoppingPrice) {
		return totalShoppingPrice.multiply(BASIC_TAX_RATE);
	}

}
