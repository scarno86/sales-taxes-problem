package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SalesTaxFunctions {

	private static final Logger log = LogManager.getLogger(SalesTaxFunctions.class);

	private static final BigDecimal ROUND_OFF_RATE = new BigDecimal("0.05");

	public static Function<BigDecimal, BigDecimal> roundingOff = valueToRound -> {

		log.debug("valueToRound  {} ", valueToRound);

		BigDecimal roundedValue = new BigDecimal(Math.ceil(valueToRound.divide(ROUND_OFF_RATE).doubleValue()))
				.multiply(ROUND_OFF_RATE);

		log.debug("roundeValue  {} ", roundedValue);

		return roundedValue;

	};

}
