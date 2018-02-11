package org.difranca.salestaxes.services.receipt.strategy.impl;

import java.math.BigDecimal;

import org.difranca.salestaxes.services.receipt.strategy.ISalesTaxCalculationStrategy;

public class ImportDutyCalculationStrategy implements ISalesTaxCalculationStrategy {

	private static final BigDecimal DUTY_RATE = new BigDecimal("0.05");
	
	@Override
	public BigDecimal calculateSalesTax(BigDecimal totalShoppingPrice) {
		return totalShoppingPrice.multiply(DUTY_RATE);
	}

}
