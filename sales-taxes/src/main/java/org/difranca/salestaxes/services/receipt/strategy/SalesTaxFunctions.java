package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;
import java.util.function.Function;

public class SalesTaxFunctions {

	private static final BigDecimal ROUND_OFF_RATE = new BigDecimal("0.05");

	public static Function<BigDecimal, BigDecimal> roundingOff = valueToRound -> valueToRound.divide(ROUND_OFF_RATE.add(new BigDecimal(0.5))).multiply(ROUND_OFF_RATE);
	
	
}
