package org.difranca.salestaxes.services.shopping;

import java.util.List;

import org.difranca.salestaxes.model.shopping.ShoppingBasket;

public interface IShoppingBasketService {

	List<ShoppingBasket> loadAllShoppingBasket();
	
	ShoppingBasket loadBasketByIdBasket(Long idBasket);
	
}
