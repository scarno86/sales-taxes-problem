package org.difranca.salestaxes.services.receipt.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.difranca.salestaxes.model.goods.GoodCategory;
import org.difranca.salestaxes.model.goods.GoodItem;
import org.difranca.salestaxes.model.shopping.ShoppingBasketItem;
import org.difranca.salestaxes.services.receipt.strategy.impl.BasicSalesTaxCalculationStrategy;
import org.difranca.salestaxes.services.receipt.strategy.impl.ImportDutyCalculationStrategy;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SalesTaxStrategyContext {

	private ISalesTaxCalculationStrategy getStrategyByGoodCategory(GoodCategory category) {

		ISalesTaxCalculationStrategy salesTaxCalculationStrategy = null;

		switch (category) {

		case GENERIC:
			salesTaxCalculationStrategy = new BasicSalesTaxCalculationStrategy();

		default:
		}

		return salesTaxCalculationStrategy;

	}

	private ISalesTaxCalculationStrategy getStrategyByGoodItem(GoodItem goodItem) {

		ISalesTaxCalculationStrategy salesTaxCalculationStrategy = null;

		salesTaxCalculationStrategy = Optional.of(goodItem)//
				.filter(item -> item.getImported())//
				.map(item -> new ImportDutyCalculationStrategy())//
				.orElse(null);

		return salesTaxCalculationStrategy;

	}

	private List<ISalesTaxCalculationStrategy> getStrategiesByGoodItem(GoodItem goodItem) {

		List<ISalesTaxCalculationStrategy> listOfSalesTaxCalculationStrategy = new ArrayList<>();

		Optional.ofNullable(getStrategyByGoodCategory(goodItem.getCategory()))//
				.ifPresent(strategy -> listOfSalesTaxCalculationStrategy.add(strategy));

		Optional.ofNullable(getStrategyByGoodItem(goodItem))//
				.ifPresent(strategy -> listOfSalesTaxCalculationStrategy.add(strategy));

		return listOfSalesTaxCalculationStrategy;

	}

	private BigDecimal applyAllSalesTaxCalculationStrategies(List<ISalesTaxCalculationStrategy> salesTaxCalculationStrategies, BigDecimal totalShoppingPrice) {

		BigDecimal salesTax = salesTaxCalculationStrategies.stream() //
				.map(strategy -> strategy.calculateSalesTax(totalShoppingPrice))//
				.map(tax -> SalesTaxFunctions.roundingOff.apply(tax))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return salesTax;
	}

	public BigDecimal executeStrategy(ShoppingBasketItem shoppingBasketItem) {

		BigDecimal itemPrice = shoppingBasketItem.getItem().getUnityPrice();
		
		List<ISalesTaxCalculationStrategy> salesTaxCalculationStrategies = getStrategiesByGoodItem(shoppingBasketItem.getItem());
		
		BigDecimal salesTax = applyAllSalesTaxCalculationStrategies(salesTaxCalculationStrategies, itemPrice);

		return salesTax;

	}
}
