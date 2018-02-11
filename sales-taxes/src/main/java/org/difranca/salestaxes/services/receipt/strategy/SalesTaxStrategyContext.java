package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import org.difranca.salestaxes.model.goods.GoodCategory;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.services.receipt.strategy.impl.BasicSalesTaxCalculationStrategy;
import org.difranca.salestaxes.services.receipt.strategy.impl.ImportDutyCalculationStrategy;
import org.difranca.salestaxes.services.shopping.ShoppingBasketFunctions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SalesTaxStrategyContext {

	private ISalesTaxCalculationStrategy importDutyStrategy = new ImportDutyCalculationStrategy();

	private ISalesTaxCalculationStrategy getStrategyByGoodCategory(GoodCategory goodCategory) {
		ISalesTaxCalculationStrategy salesTaxCalculationStrategy = null;

		switch (goodCategory) {
		case GENERIC:
			salesTaxCalculationStrategy = new BasicSalesTaxCalculationStrategy();
		default:
		}

		return salesTaxCalculationStrategy;

	}

	public BigDecimal executeStrategy(ShoppingBasketItem shoppingBasketItem) {

		ISalesTaxCalculationStrategy salesTaxCalculationStrategy = getStrategyByGoodCategory(
				shoppingBasketItem.getItem().getCategory());

		BigDecimal totalShoppingPrice = ShoppingBasketFunctions.calculateTotalShoppingPriceItem
				.apply(shoppingBasketItem);

		BigDecimal salesTax = Optional.ofNullable(salesTaxCalculationStrategy)//
				.map(strategy -> strategy.calculateSalesTax(totalShoppingPrice))//
				.orElse(BigDecimal.ZERO);

		// Apply default strategy for additional sales tax applicable on all imported
		// goods
		BigDecimal importDuty = importDutyStrategy.calculateSalesTax(totalShoppingPrice);

		BigDecimal totalSalesTaxes = Stream.of(salesTax, importDuty).reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalSalesTaxes;

	}
}
